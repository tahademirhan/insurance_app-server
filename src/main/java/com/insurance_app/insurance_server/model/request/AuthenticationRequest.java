package com.insurance_app.insurance_server.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    private String email;
    private String password;
}
