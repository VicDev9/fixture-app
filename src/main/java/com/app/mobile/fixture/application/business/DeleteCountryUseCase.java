package com.app.mobile.fixture.application.business;


import com.app.mobile.fixture.domain.dto.CountryDto;

public interface DeleteCountryUseCase {

    CountryDto deleteCountry(String id);

}
