package com.app.mobile.fixture.application.business;


import com.app.mobile.fixture.domain.dto.CountryDto;
import com.app.mobile.fixture.domain.request.CountryUpdateRequest;

public interface UpdateCountryUseCase {

    CountryDto updateCountry(CountryUpdateRequest countryRequest);
}
