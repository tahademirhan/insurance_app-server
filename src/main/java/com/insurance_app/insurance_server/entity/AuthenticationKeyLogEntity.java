package com.insurance_app.insurance_server.entity;

import com.insurance_app.insurance_server.entity.base.BaseEntity;
import com.insurance_app.insurance_server.entity.lookup.AuthenticationReasonLookupEntity;
import com.insurance_app.insurance_server.entity.lookup.AuthenticationTypeLookupEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "authentication_key_log")
@Getter
@Setter
public class AuthenticationKeyLogEntity extends BaseEntity {

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "expire_date", nullable = false)
    private LocalDateTime expireDate;

    @Column(name = "vkey")
    private String key;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type", referencedColumnName = "id")
    private AuthenticationTypeLookupEntity authenticationType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reason", referencedColumnName = "id")
    private AuthenticationReasonLookupEntity authenticationReason;

    @Column(name = "used", columnDefinition = "boolean default false")
    private Boolean used;
}
