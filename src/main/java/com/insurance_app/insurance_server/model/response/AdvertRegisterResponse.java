package com.insurance_app.insurance_server.model.response;

import com.insurance_app.insurance_server.model.Advert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertRegisterResponse {

    private Advert advert;
}
