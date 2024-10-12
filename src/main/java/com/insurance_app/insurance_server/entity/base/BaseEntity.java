package com.insurance_app.insurance_server.entity.base;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

    @Column(name = "create_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createDate;
    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;
    @Column(name = "create_user", nullable = false, columnDefinition = "varchar(255) default 'SYSTEM'")
    private String createUser;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
}

