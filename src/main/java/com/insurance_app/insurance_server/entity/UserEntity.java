package com.insurance_app.insurance_server.entity;

import com.insurance_app.insurance_server.entity.base.BaseEntity;
import com.insurance_app.insurance_server.entity.lookup.UserRoleLookupEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends BaseEntity {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private UserRoleLookupEntity role;

    @OneToOne
    @JoinColumn(name = "corporate", referencedColumnName = "id")
    private CorporateEntity corporate;
}
