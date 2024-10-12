package com.insurance_app.insurance_server.repository;

import com.insurance_app.insurance_server.entity.AuthenticationKeyLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AuthenticationKeyLogRepository extends JpaRepository<AuthenticationKeyLogEntity, Integer> {

    @Query("from AuthenticationKeyLogEntity  au where  au.authenticationReason.key = :reason and au.used = false  "
            + "and au.key= :key and :time<= au.expireDate "
            + "and (:receiver is null or au.receiver=:receiver)")
    Optional<AuthenticationKeyLogEntity> findAuthKey(String receiver, String key, String reason, LocalDateTime time);
}
