package com.insurance_app.insurance_server.repository;

import com.insurance_app.insurance_server.entity.lookup.AdvertTypeLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertTypeRepository extends JpaRepository<AdvertTypeLookupEntity, Integer> {

    AdvertTypeLookupEntity findByKey(String key);
}
