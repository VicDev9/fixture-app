package com.app.mobile.fixture.application.business;


import com.app.mobile.fixture.domain.dto.CountryDto;

import java.util.List;

public interface ReadCountryUseCase {

    public CountryDto findCountry(String name);

    public List<CountryDto> findAllCountries();
}
