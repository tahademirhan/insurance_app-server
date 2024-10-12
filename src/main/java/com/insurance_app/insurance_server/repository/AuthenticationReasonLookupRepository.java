package com.insurance_app.insurance_server.repository;

import com.insurance_app.insurance_server.entity.lookup.AuthenticationReasonLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationReasonLookupRepository extends JpaRepository<AuthenticationReasonLookupEntity, Integer> {

    AuthenticationReasonLookupEntity findByKey(String key);
}
