package com.insurance_app.insurance_server.repository;

import com.insurance_app.insurance_server.entity.lookup.AdvertInsuredTypeLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertInsuredTypeRepository extends JpaRepository<AdvertInsuredTypeLookupEntity, Integer> {

    AdvertInsuredTypeLookupEntity findByKey(String key);
}
