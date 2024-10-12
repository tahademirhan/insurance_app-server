package com.insurance_app.insurance_server.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationKeyValidateRequest {

    @NotEmpty
    private String type;
    @NotEmpty
    private String reason;
    @NotEmpty
    private String to;
    @NotEmpty
    private String key;
}
