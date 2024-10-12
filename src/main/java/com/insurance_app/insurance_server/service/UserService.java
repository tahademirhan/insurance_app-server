package com.insurance_app.insurance_server.service;

import com.insurance_app.insurance_server.core.exception.BusinessException;
import com.insurance_app.insurance_server.core.exception.ExceptionCodes;
import com.insurance_app.insurance_server.entity.UserEntity;
import com.insurance_app.insurance_server.entity.lookup.UserRoleLookupEntity;
import com.insurance_app.insurance_server.model.request.UserRegisterRequest;
import com.insurance_app.insurance_server.repository.UserRepository;
import com.insurance_app.insurance_server.entity.lookup.AuthenticationReasonLookupEntity;
import com.insurance_app.insurance_server.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.insurance_app.insurance_server.model.response.UserForgotPasswordResponse;
import com.insurance_app.insurance_server.model.response.UserRegisterResponse;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final CorporateService corporateService;

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        Optional<UserEntity> userOptional = userRepository.findByEmail(username, null);
        if(userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            if(userEntity.getStatus()) {
                user.setId(userEntity.getId());
                user.setUsername(userEntity.getEmail());
                user.setPassword(userEntity.getPassword());
                user.setUserRole(userEntity.getRole().getKey().toUpperCase(Locale.ENGLISH));

                if(!userEntity.getRole().getKey().equals(UserRoleLookupEntity.GUEST.getKey())) {
                    user.setCorporate(userEntity.getCorporate().getId());
                    user.setCorporateName(userEntity.getCorporate().getName());
                }
            }
        }
        return user;
    }

    public UserRegisterResponse register(UserRegisterRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setCreateUser("SYSTEM");
        userEntity.setEmail(request.getEmail().trim());
        userEntity.setPassword(encodePassword(request.getPassword().trim()));
        userEntity.setName(request.getName().trim());
        userEntity.setSurname(request.getSurname().trim());
        userEntity.setStatus(true);
        //TODO
        /* Sirket mail domain kontrolu gelince duzenlenecek */
        userEntity.setCorporate(corporateService.findById(1));
        userEntity.setRole(UserRoleLookupEntity.INSURER);
        /**/
        userEntity = userRepository.save(userEntity);
        return new UserRegisterResponse(userEntity.getId());
    }

    public UserForgotPasswordResponse forgotPassword(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email, true).orElseThrow(() -> new BusinessException(ExceptionCodes.AUTH001));

        userEntity.setPassword(encodePassword(password));
        userRepository.save(userEntity);

        return new UserForgotPasswordResponse();
    }

    public String encodePassword(String rawPassword) {
        var bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public void checkUser(String email, String reason) {
        var userOptional = userRepository.findByEmail(email, null);

        if(reason.equals(AuthenticationReasonLookupEntity.REGISTER) && userOptional.isPresent()) {
            throw new BusinessException(ExceptionCodes.AUTH002);
        }

        if((reason.equals(AuthenticationReasonLookupEntity.FORGOT_PASSWORD) || reason.equals(AuthenticationReasonLookupEntity.LOGIN)) && userOptional.isEmpty()) {
            throw new BusinessException(ExceptionCodes.AUTH003);
        }
    }
}
