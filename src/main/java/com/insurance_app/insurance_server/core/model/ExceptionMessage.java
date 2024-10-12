package com.insurance_app.insurance_server.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {

    private List<String> messages;
    private LocalDateTime time;
    private String code;
}
