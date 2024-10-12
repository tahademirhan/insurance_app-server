package com.insurance_app.insurance_server.repository;

import com.insurance_app.insurance_server.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Integer> {

    Optional<ConfigurationEntity> findByKey(String key);
}
