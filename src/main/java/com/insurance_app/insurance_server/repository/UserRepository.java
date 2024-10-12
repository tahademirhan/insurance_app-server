package com.insurance_app.insurance_server.repository;

import com.insurance_app.insurance_server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("from UserEntity u where u.email= :email and ( :status is null or u.status=:status)")
    Optional<UserEntity> findByEmail(String email, Boolean status);
}
