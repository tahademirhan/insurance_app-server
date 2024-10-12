package com.insurance_app.insurance_server.entity.lookup;

import com.insurance_app.insurance_server.entity.base.BaseLookupEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lk_advert_type")
@Getter
@Setter
public class AdvertTypeLookupEntity extends BaseLookupEntity {

    public static final String CAR = "car";
    public static final String HEALTH = "health";
    public static final String OTHER = "other";
}
