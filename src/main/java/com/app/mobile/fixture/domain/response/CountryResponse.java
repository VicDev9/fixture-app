package com.app.mobile.fixture.domain.response;


import com.app.mobile.fixture.domain.dto.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse {

    private String message;
    private CountryDto countryDto;
}
