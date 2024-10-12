package com.insurance_app.insurance_server.entity;

import com.insurance_app.insurance_server.entity.base.BaseEntity;
import com.insurance_app.insurance_server.entity.lookup.CorporateTypeLookupEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "corporates")
@Getter
@Setter
public class CorporateEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    private CorporateTypeLookupEntity type;
}
