package com.insurance_app.insurance_server.repository;

import com.insurance_app.insurance_server.entity.lookup.AdvertStatusLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertStatusRepository extends JpaRepository<AdvertStatusLookupEntity, Integer> {

    AdvertStatusLookupEntity findByKey(String key);
}
