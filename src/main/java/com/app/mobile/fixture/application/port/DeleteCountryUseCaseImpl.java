package com.app.mobile.fixture.application.port;


import com.app.mobile.fixture.application.business.DeleteCountryUseCase;
import com.app.mobile.fixture.domain.document.CountriesDocument;
import com.app.mobile.fixture.domain.dto.CountryDto;
import com.app.mobile.fixture.infrastructure.repository.CountryRepository;
import com.app.mobile.fixture.infrastructure.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeleteCountryUseCaseImpl implements DeleteCountryUseCase {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public CountryDto deleteCountry(String id) {
        CountriesDocument countryDocument = countryRepository.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The country that you want to delete doesn't exist or it already deleted"));
        countryDocument.setStatus(Constants.INACTIVE);
        countryRepository.save(countryDocument);
        return new ModelMapper().map(countryDocument, CountryDto.class);
    }
}
