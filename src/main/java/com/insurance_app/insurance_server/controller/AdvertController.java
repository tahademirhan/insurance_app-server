package com.insurance_app.insurance_server.controller;

import com.insurance_app.insurance_server.model.request.AdvertRegisterRequest;
import com.insurance_app.insurance_server.model.response.AdvertQueryResponse;
import com.insurance_app.insurance_server.model.response.AdvertRegisterResponse;
import com.insurance_app.insurance_server.service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/advert")
@RestController
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;

    @PostMapping("register")
    public ResponseEntity<AdvertRegisterResponse> register(@RequestBody AdvertRegisterRequest request) {
        return ResponseEntity.ok(advertService.register(request));
    }

    @GetMapping("query")
    public ResponseEntity<AdvertQueryResponse> query() {
        return ResponseEntity.ok(advertService.query());
    }

    @GetMapping("corporate-query")
    public ResponseEntity<AdvertQueryResponse> corporateQuery() {
        return ResponseEntity.ok(advertService.corporateQuery());
    }
}
