package com.insurance_app.insurance_server.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    private String email;
    private String password;
    private String name;
    private String surname;
}
