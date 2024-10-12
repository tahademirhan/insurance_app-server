package com.insurance_app.insurance_server.controller;

import com.insurance_app.insurance_server.model.request.AuthenticationKeyRequest;
import com.insurance_app.insurance_server.model.request.AuthenticationKeyValidateRequest;
import com.insurance_app.insurance_server.model.response.AuthenticationKeyResponse;
import com.insurance_app.insurance_server.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/verification")
@RestController
@RequiredArgsConstructor
public class VerificationController {

    private final AuthenticationService authenticationService;

    @PostMapping("send/auth-key")
    public ResponseEntity<AuthenticationKeyResponse> sendAuthKey(@RequestBody AuthenticationKeyRequest request) {
        return ResponseEntity.ok(authenticationService.sendAuthenticationKey(request));
    }

    @PostMapping("validate/auth-key")
    public ResponseEntity<AuthenticationKeyResponse> validateAuthKey(@RequestBody AuthenticationKeyValidateRequest request) {
        return ResponseEntity.ok(authenticationService.validateAuthKey(request));
    }
}
