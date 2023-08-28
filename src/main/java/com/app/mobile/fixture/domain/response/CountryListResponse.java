package com.app.mobile.fixture.domain.response;

import com.app.mobile.fixture.domain.dto.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryListResponse {

    private String message;
    private List<CountryDto> countryList;

}
