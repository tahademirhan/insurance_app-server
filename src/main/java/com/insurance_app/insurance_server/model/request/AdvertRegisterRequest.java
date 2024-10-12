package com.insurance_app.insurance_server.model.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AdvertRegisterRequest {

    private String policyID;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal amountOfPremium;
    private String currency;
    private String type;
    private String paymentPeriod;

    private String insuredType;
    private String name;
    private String surname;
    private String nationality;
    private String nationalID;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private String address;

    private String coverageDetails;
    private String paymentDetails;
    private String termsAndConditions;
    private String otherDetails;
}
