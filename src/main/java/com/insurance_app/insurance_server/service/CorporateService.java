package com.insurance_app.insurance_server.service;

import com.insurance_app.insurance_server.core.UserContext;
import com.insurance_app.insurance_server.entity.CorporateEntity;
import com.insurance_app.insurance_server.model.request.CorporateRegisterRequest;
import com.insurance_app.insurance_server.model.response.CorporateRegisterResponse;
import com.insurance_app.insurance_server.repository.CorporateRepository;
import com.insurance_app.insurance_server.repository.CorporateTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CorporateService {

    private final CorporateRepository corporateRepository;
    private final CorporateTypeRepository corporateTypeRepository;

    public CorporateEntity findById(Integer id) {
        return corporateRepository.findById(id).orElseThrow();
    }

    public CorporateRegisterResponse register(CorporateRegisterRequest request) {
        CorporateEntity entity = new CorporateEntity();
        entity.setCreateUser(UserContext.getUser().getId().toString());
        entity.setName(request.getName());
        entity.setType(corporateTypeRepository.findByKey(request.getType()));
        entity = corporateRepository.save(entity);
        return new CorporateRegisterResponse(entity.getId());
    }
}
