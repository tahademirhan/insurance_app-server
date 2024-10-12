package com.insurance_app.insurance_server.entity.lookup;

import com.insurance_app.insurance_server.entity.base.BaseLookupEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lk_corporate_type")
@Getter
@Setter
public class CorporateTypeLookupEntity extends BaseLookupEntity {

    public static final String INSURANCE = "insurance";
    public static final String REINSURANCE = "reinsurance";
}
