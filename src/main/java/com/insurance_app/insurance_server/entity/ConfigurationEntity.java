package com.insurance_app.insurance_server.entity;

import com.insurance_app.insurance_server.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "configuration")
@Getter
@Setter
public class ConfigurationEntity extends BaseEntity {

    @Column(name = "vkey")
    private String key;

    @Column(name = "vvalue")
    private String value;

    @Column(name = "description")
    private String description;

    @Column(name = "status", columnDefinition = "boolean default true")
    private Boolean status;
}
