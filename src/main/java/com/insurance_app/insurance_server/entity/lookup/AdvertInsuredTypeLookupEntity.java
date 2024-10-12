package com.insurance_app.insurance_server.entity.lookup;

import com.insurance_app.insurance_server.entity.base.BaseLookupEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lk_advert_insured_type")
@Getter
@Setter
public class AdvertInsuredTypeLookupEntity extends BaseLookupEntity {

    public static final String INDIVIDUAL = "individual";
    public static final String CORPORATE = "corporate";
}
