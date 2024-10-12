package com.insurance_app.insurance_server.entity.lookup;

import com.insurance_app.insurance_server.entity.base.BaseLookupEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lk_authentication_reason")
@Getter
@Setter
public class AuthenticationReasonLookupEntity extends BaseLookupEntity {

    public static final String LOGIN = "login";
    public static final String REGISTER = "register";
    public static final String FORGOT_PASSWORD = "forgot-password";
}
