package com.insurance_app.insurance_server.service;

import com.insurance_app.insurance_server.core.UserContext;
import com.insurance_app.insurance_server.entity.AdvertEntity;
import com.insurance_app.insurance_server.entity.lookup.AdvertStatusLookupEntity;
import com.insurance_app.insurance_server.model.Advert;
import com.insurance_app.insurance_server.model.request.AdvertRegisterRequest;
import com.insurance_app.insurance_server.model.response.AdvertQueryResponse;
import com.insurance_app.insurance_server.model.response.AdvertRegisterResponse;
import com.insurance_app.insurance_server.repository.AdvertInsuredTypeRepository;
import com.insurance_app.insurance_server.repository.AdvertRepository;
import com.insurance_app.insurance_server.repository.AdvertStatusRepository;
import com.insurance_app.insurance_server.repository.AdvertTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final CorporateService corporateService;

    private final AdvertRepository advertRepository;
    private final AdvertStatusRepository advertStatusRepository;
    private final AdvertTypeRepository advertTypeRepository;
    private final AdvertInsuredTypeRepository advertInsuredTypeRepository;

    public AdvertRegisterResponse register(AdvertRegisterRequest request) {
        AdvertEntity advert = new AdvertEntity();
        advert.setCreateUser(UserContext.getUser().getId().toString());
        advert.setStatus(advertStatusRepository.findByKey(AdvertStatusLookupEntity.ACTIVE));
        advert.setPolicyID(request.getPolicyID());
        advert.setTitle(request.getTitle());
        advert.setDescription(request.getDescription());
        advert.setAmountOfPremium(request.getAmountOfPremium());
        advert.setCurrency(request.getCurrency());
        advert.setType(advertTypeRepository.findByKey(request.getType()));
        advert.setPaymentPeriod(request.getPaymentPeriod());
        advert.setStartDate(request.getStartDate());
        advert.setEndDate(request.getEndDate());
        advert.setInsuredType(advertInsuredTypeRepository.findByKey(request.getInsuredType()));
        advert.setName(request.getName());
        advert.setSurname(request.getSurname());
        advert.setNationality(request.getNationality());
        advert.setNationalID(request.getNationalID());
        advert.setPhone(request.getPhone());
        advert.setEmail(request.getEmail());
        advert.setBirthDate(request.getBirthDate());
        advert.setAddress(request.getAddress());
        advert.setCoverageDetails(request.getCoverageDetails());
        advert.setPaymentDetails(request.getPaymentDetails());
        advert.setTermsAndConditions(request.getTermsAndConditions());
        advert.setOtherDetails(request.getOtherDetails());
        advert.setCorporate(corporateService.findById(UserContext.getUser().getCorporate()));

        advert = advertRepository.save(advert);

        return new AdvertRegisterResponse(convertAdvertEntity(advert));
    }

    private Advert convertAdvertEntity(AdvertEntity entity) {
        Advert model = new Advert();

        model.setId(entity.getId());
        model.setCreateDate(entity.getCreateDate());
        model.setStatus(entity.getStatus().getValue());
        model.setPolicyID(entity.getPolicyID());
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());
        model.setStartDate(entity.getStartDate());
        model.setEndDate(entity.getEndDate());
        model.setAmountOfPremium(entity.getAmountOfPremium());
        model.setCurrency(entity.getCurrency());
        model.setType(entity.getType().getValue());
        model.setPaymentPeriod(entity.getPaymentPeriod());
        model.setInsuredType(entity.getInsuredType().getValue());
        model.setName(entity.getName());
        model.setSurname(entity.getSurname());
        model.setNationality(entity.getNationality());
        model.setNationalID(entity.getNationalID());
        model.setPhone(entity.getPhone());
        model.setEmail(entity.getEmail());
        model.setAddress(entity.getAddress());
        model.setBirthDate(entity.getBirthDate());
        model.setCoverageDetails(entity.getCoverageDetails());
        model.setPaymentDetails(entity.getPaymentDetails());
        model.setTermsAndConditions(entity.getTermsAndConditions());
        model.setOtherDetails(entity.getOtherDetails());
        model.setCorporateID(entity.getCorporate().getId());
        model.setCorporateName(entity.getCorporate().getName());

        return model;
    }

    public AdvertQueryResponse query() {
        var entityList = advertRepository.query(AdvertStatusLookupEntity.ACTIVE, null);
        var advertList = entityList.stream().map(this::convertAdvertEntity).collect(Collectors.toList());
        return new AdvertQueryResponse(advertList);
    }

    public AdvertQueryResponse corporateQuery() {
        var entityList = advertRepository.query(AdvertStatusLookupEntity.ACTIVE, UserContext.getUser().getCorporate());
        var advertList = entityList.stream().map(this::convertAdvertEntity).collect(Collectors.toList());
        return new AdvertQueryResponse(advertList);
    }
}
