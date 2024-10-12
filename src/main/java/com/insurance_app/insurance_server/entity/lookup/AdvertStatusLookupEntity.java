package com.insurance_app.insurance_server.entity.lookup;

import com.insurance_app.insurance_server.entity.base.BaseLookupEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lk_advert_status")
@Getter
@Setter
public class AdvertStatusLookupEntity extends BaseLookupEntity {

    public static final String ACTIVE = "active";
    public static final String PASSIVE = "passive";
    public static final String PENDING = "pending";
}
