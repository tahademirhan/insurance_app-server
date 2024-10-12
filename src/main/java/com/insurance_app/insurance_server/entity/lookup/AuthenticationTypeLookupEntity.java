package com.insurance_app.insurance_server.entity.lookup;

import com.insurance_app.insurance_server.entity.base.BaseLookupEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lk_authentication_type")
@Getter
@Setter
public class AuthenticationTypeLookupEntity extends BaseLookupEntity {

    public static final String SMS = "sms";
    public static final String EMAIL = "email";
    public static final String SMSEMAIL = "sms_email";
}
