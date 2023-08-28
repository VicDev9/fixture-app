package com.app.mobile.fixture.application.port;


import com.app.mobile.fixture.application.business.UpdateCountryUseCase;
import com.app.mobile.fixture.domain.document.CountriesDocument;
import com.app.mobile.fixture.domain.dto.CountryDto;
import com.app.mobile.fixture.domain.request.CountryUpdateRequest;
import com.app.mobile.fixture.infrastructure.repository.CountryRepository;
import com.app.mobile.fixture.infrastructure.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateCountryUseCaseImpl implements UpdateCountryUseCase {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public CountryDto updateCountry(CountryUpdateRequest countryRequest) {
        CountriesDocument countryDocument = countryRepository.findByIdAndStatus(countryRequest.getId(), Constants.ACTIVE)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The country that you want to update doesn't exist"));
        countryDocument.setName(countryRequest.getName());
        countryDocument.setConfederation(countryRequest.getConfederation());
        countryDocument.setContinent(countryRequest.getContinent());
        countryRepository.save(countryDocument);
        return new ModelMapper().map(countryDocument, CountryDto.class);
    }
}
