package com.app.mobile.fixture.application.business;


import com.app.mobile.fixture.domain.dto.CountryDto;
import com.app.mobile.fixture.domain.request.CountryRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CreateCountryUseCase {

    Map<String, String> createCountry(List<CountryRequest> countryRequest);
}
