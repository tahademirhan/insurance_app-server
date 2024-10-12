package com.insurance_app.insurance_server.entity;

import com.insurance_app.insurance_server.entity.base.BaseEntity;
import com.insurance_app.insurance_server.entity.lookup.AdvertInsuredTypeLookupEntity;
import com.insurance_app.insurance_server.entity.lookup.AdvertStatusLookupEntity;
import com.insurance_app.insurance_server.entity.lookup.AdvertTypeLookupEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "adverts")
@Getter
@Setter
public class AdvertEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "status", referencedColumnName = "id")
    private AdvertStatusLookupEntity status;

    @Column(name = "policy_id")
    private String policyID;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "amount_of_premium")
    private BigDecimal amountOfPremium;

    @Column(name = "currency")
    private String currency;

    @OneToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    private AdvertTypeLookupEntity type;

    @Column(name = "payment_period")
    private String paymentPeriod;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "insured_type", referencedColumnName = "id")
    private AdvertInsuredTypeLookupEntity insuredType;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "national_id")
    private String nationalID;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "coverage_details", columnDefinition = "TEXT")
    private String coverageDetails;

    @Column(name = "payment_details", columnDefinition = "TEXT")
    private String paymentDetails;

    @Column(name = "terms_and_conditions", columnDefinition = "TEXT")
    private String termsAndConditions;

    @Column(name = "other_details", columnDefinition = "TEXT")
    private String otherDetails;

    @OneToOne
    @JoinColumn(name = "corporate", referencedColumnName = "id")
    private CorporateEntity corporate;
}
