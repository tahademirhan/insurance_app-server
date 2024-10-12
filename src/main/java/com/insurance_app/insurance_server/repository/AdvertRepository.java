package com.insurance_app.insurance_server.repository;

import com.insurance_app.insurance_server.entity.AdvertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertRepository extends JpaRepository<AdvertEntity, Integer> {

    @Query("from AdvertEntity e where ((:status is null or e.status.key = :status) and (:corporateID is null or e.corporate.id = :corporateID)) order by e.createDate desc ")
    List<AdvertEntity> query(String status, Integer corporateID);
}
