package com.insurance_app.insurance_server.repository;

import com.insurance_app.insurance_server.entity.lookup.AuthenticationTypeLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTypeLookupRepository extends JpaRepository<AuthenticationTypeLookupEntity, Integer> {

    AuthenticationTypeLookupEntity findByKey(String key);
}
