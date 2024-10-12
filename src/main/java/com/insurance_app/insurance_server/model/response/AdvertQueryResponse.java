package com.insurance_app.insurance_server.model.response;

import com.insurance_app.insurance_server.core.model.BaseResponse;
import com.insurance_app.insurance_server.model.Advert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertQueryResponse extends BaseResponse {

    private List<Advert> adverts;
}
