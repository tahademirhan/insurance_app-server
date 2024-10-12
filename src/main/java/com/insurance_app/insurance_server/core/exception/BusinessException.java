package com.insurance_app.insurance_server.core.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private String code;
    private Boolean translate;

    public BusinessException(String code) {
        this.code = code;
    }
    public BusinessException(String code,Boolean translate) {
        this.code = code;
        this.translate=translate;
    }
}
