package com.insurance_app.insurance_server.core;

import com.insurance_app.insurance_server.core.exception.BusinessException;
import com.insurance_app.insurance_server.core.model.ExceptionMessage;
import com.insurance_app.insurance_server.core.util.ResourceBundleUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        return handle(List.of("Bad Credentionls"), null, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        String message;
        if(ex.getTranslate()!=null && !ex.getTranslate()){
            message= ex.getCode();
        }else{
            message= ResourceBundleUtil.getValue(ex.getCode());
        }

        return handle( List.of(message), ex.getCode(), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream()
                .map(m -> ResourceBundleUtil.getValue(m.getDefaultMessage())).collect(Collectors.toList());
        return handle(validationList, "VALIDATION_ERROR", HttpStatus.OK);
    }

    private ResponseEntity<Object> handle(List<String> messages, String code, HttpStatus httpStatus) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(messages, LocalDateTime.now(), code);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        return new ResponseEntity<>(exceptionMessage, httpHeaders, httpStatus);
    }
}
