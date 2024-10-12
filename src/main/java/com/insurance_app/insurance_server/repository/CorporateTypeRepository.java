package com.insurance_app.insurance_server.repository;

import com.insurance_app.insurance_server.entity.lookup.CorporateTypeLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateTypeRepository extends JpaRepository<CorporateTypeLookupEntity, Integer> {

    CorporateTypeLookupEntity findByKey(String key);
}
