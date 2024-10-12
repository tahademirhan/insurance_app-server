package com.insurance_app.insurance_server.service;

import com.insurance_app.insurance_server.constant.ConfigConst;
import com.insurance_app.insurance_server.core.exception.BusinessException;
import com.insurance_app.insurance_server.core.exception.ExceptionCodes;
import com.insurance_app.insurance_server.model.request.AuthenticationKeyValidateRequest;
import com.insurance_app.insurance_server.repository.AuthenticationKeyLogRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.insurance_app.insurance_server.entity.AuthenticationKeyLogEntity;
import com.insurance_app.insurance_server.entity.lookup.AuthenticationTypeLookupEntity;
import com.insurance_app.insurance_server.model.request.AuthenticationKeyRequest;
import com.insurance_app.insurance_server.model.response.AuthenticationKeyResponse;
import com.insurance_app.insurance_server.model.response.AuthenticationResponse;
import com.insurance_app.insurance_server.repository.AuthenticationReasonLookupRepository;
import com.insurance_app.insurance_server.repository.AuthenticationTypeLookupRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Getter
@Setter
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final ConfigurationService configurationService;
    private final EmailService emailService;
    private final AuthenticationTypeLookupRepository authenticationTypeLookupRepository;
    private final AuthenticationReasonLookupRepository authenticationReasonLookupRepository;
    private final AuthenticationKeyLogRepository authenticationKeyLogRepository;

    public AuthenticationResponse authenticate(String email, String password) {
        try {
            var authenticate =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            UserDetails userDetails = userService.loadUserByUsername(authenticate.getName());
            var token = jwtService.generateToken(userDetails);
            //userHistoryService.addHistoryLog(UserHistoryTypeLookup.LOGIN, username, token.substring(token.length() - 10));
            return new AuthenticationResponse(token, jwtService.extractExpiration(token).getTime());
        } catch (BadCredentialsException badCredentialsException) {
            throw new BusinessException(ExceptionCodes.AUTH001);
        }
    }

    public AuthenticationKeyResponse sendAuthenticationKey(AuthenticationKeyRequest request) {
        if(request.getType().equals(AuthenticationTypeLookupEntity.EMAIL)) {
            userService.checkUser(request.getTo().trim(), request.getReason());
            sendKey(request);
        }else {
            throw new BusinessException(ExceptionCodes.AUTHKEY001);
        }

        return new AuthenticationKeyResponse();
    }

    private void sendKey(AuthenticationKeyRequest request) {
        var emailKeyExpireTime = configurationService.getByKey(ConfigConst.emailKeyExpireTime, Integer.class);
        var authKey = generateAuthKey(request.getType());
        var authenticationKeyLogEntity = new AuthenticationKeyLogEntity();
        authenticationKeyLogEntity.setKey(authKey);
        authenticationKeyLogEntity.setAuthenticationType(authenticationTypeLookupRepository.findByKey(request.getType()));
        authenticationKeyLogEntity.setAuthenticationReason(authenticationReasonLookupRepository.findByKey(request.getReason()));
        authenticationKeyLogEntity.setReceiver(request.getTo());
        authenticationKeyLogEntity.setUsed(false);
        authenticationKeyLogEntity.setCreateUser("SYSTEM");
        authenticationKeyLogEntity.setCreateDate(LocalDateTime.now());
        authenticationKeyLogEntity.setExpireDate(LocalDateTime.now().plusSeconds(emailKeyExpireTime));
        authenticationKeyLogRepository.save(authenticationKeyLogEntity);
        log.info("SMS is sending to " + request.getTo() + " key " + authKey);
        emailService.sendEmail(request.getTo(), getContent(authKey));
    }

    private String generateAuthKey(String type) {
        String charset;
        Integer length;
        if (type.equals(AuthenticationTypeLookupEntity.EMAIL)) {
            charset = configurationService.getByKey(ConfigConst.emailKeyCharSet, String.class);
            length = configurationService.getByKey(ConfigConst.emailKeyLength, Integer.class);
        } else {
            throw new BusinessException(ExceptionCodes.AUTHKEY001);
        }

        String[] charSetArray = charset.split("");
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < length; i++) {
            key.append(charSetArray[(int) (Math.random() * charset.length())]);
        }
        return key.toString();
    }

    private String getContent(String key) {
        return configurationService.getByKey(ConfigConst.emailAuthenticationContent, String.class).replace("{key}", key);
    }

    public AuthenticationKeyResponse validateAuthKey(AuthenticationKeyValidateRequest request) {
        if (request.getType().equals(AuthenticationTypeLookupEntity.EMAIL)) {
            Optional<AuthenticationKeyLogEntity> authKey = authenticationKeyLogRepository.findAuthKey(request.getTo(),
                    request.getKey(), request.getReason(), LocalDateTime.now());
            if (authKey.isEmpty()) {
                throw new BusinessException(ExceptionCodes.AUTHKEY002);
            }

            usedAuthKeyUpdate(authKey);
            /*userHistoryService.addHistoryLog(UserHistoryTypeLookup.SMS_VALIDATION, request.getTo(),
                    "reason: " + request.getReason() + "->" + request.getTo());*/

            return new AuthenticationKeyResponse();
        }

        throw new BusinessException(ExceptionCodes.AUTHKEY001);
    }

    private void usedAuthKeyUpdate(Optional<AuthenticationKeyLogEntity> authKey) {
        if (authKey.isPresent()) {
            var authenticationKeyLogEntity = authKey.get();
            authenticationKeyLogEntity.setUsed(true);
            authenticationKeyLogEntity.setUpdateDate(LocalDateTime.now());
            authenticationKeyLogRepository.save(authenticationKeyLogEntity);
        }
    }
}
