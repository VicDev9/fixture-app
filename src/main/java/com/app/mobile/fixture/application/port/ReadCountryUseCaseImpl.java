package com.app.mobile.fixture.application.port;


import com.app.mobile.fixture.application.business.ReadCountryUseCase;
import com.app.mobile.fixture.domain.document.CountriesDocument;
import com.app.mobile.fixture.domain.dto.CountryDto;
import com.app.mobile.fixture.infrastructure.repository.CountryRepository;
import com.app.mobile.fixture.infrastructure.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadCountryUseCaseImpl implements ReadCountryUseCase {

    @Autowired
    private CountryRepository countryRepository;
    CountryDto countryDto = new CountryDto();

    @Override
    public CountryDto findCountry(String name) {
        CountriesDocument countriesDocument = countryRepository.findByNameAndStatus(name, Constants.ACTIVE)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country doesn't exist"));
        return new ModelMapper().map(countriesDocument, CountryDto.class);
    }

    @Override
    public List<CountryDto> findAllCountries() {
        List<CountriesDocument> list = countryRepository.findByStatus(Constants.ACTIVE);
        if(list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return list.stream().map(element -> {
            countryDto = new ModelMapper().map(element, CountryDto.class);
            return countryDto;
        }).collect(Collectors.toList());
    }
}
