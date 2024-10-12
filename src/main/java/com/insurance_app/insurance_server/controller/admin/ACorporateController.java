package com.insurance_app.insurance_server.controller.admin;


import com.insurance_app.insurance_server.model.request.CorporateRegisterRequest;
import com.insurance_app.insurance_server.model.response.CorporateRegisterResponse;
import com.insurance_app.insurance_server.service.CorporateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/corporate")
@RequiredArgsConstructor
public class ACorporateController {

    private final CorporateService corporateService;

    @PostMapping("register")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<CorporateRegisterResponse> register(@RequestBody CorporateRegisterRequest request) {
        return ResponseEntity.ok(corporateService.register(request));
    }
}
