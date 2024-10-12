package com.insurance_app.insurance_server.repository;

import com.insurance_app.insurance_server.entity.CorporateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateRepository extends JpaRepository<CorporateEntity, Integer> {
}
