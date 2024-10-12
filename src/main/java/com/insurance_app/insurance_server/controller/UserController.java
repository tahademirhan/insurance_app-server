package com.insurance_app.insurance_server.controller;

import com.insurance_app.insurance_server.model.request.AuthenticationRequest;
import com.insurance_app.insurance_server.model.request.UserForgotPasswordRequest;
import com.insurance_app.insurance_server.model.request.UserRegisterRequest;
import com.insurance_app.insurance_server.model.response.AuthenticationResponse;
import com.insurance_app.insurance_server.model.response.UserForgotPasswordResponse;
import com.insurance_app.insurance_server.model.response.UserRegisterResponse;
import com.insurance_app.insurance_server.service.AuthenticationService;
import com.insurance_app.insurance_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request.getEmail(), request.getPassword()));
    }

    @PostMapping("forgot-password")
    public ResponseEntity<UserForgotPasswordResponse> forgotPassword(@RequestBody UserForgotPasswordRequest request) {
        return ResponseEntity.ok(userService.forgotPassword(request.getEmail(), request.getPassword()));
    }
}