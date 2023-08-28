package com.app.mobile.fixture.application.port;


import com.app.mobile.fixture.application.business.CreateCountryUseCase;
import com.app.mobile.fixture.domain.document.CountriesDocument;
import com.app.mobile.fixture.domain.dto.CountryDto;
import com.app.mobile.fixture.domain.request.CountryRequest;
import com.app.mobile.fixture.infrastructure.repository.CountryRepository;
import com.app.mobile.fixture.infrastructure.util.Constants;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.app.mobile.fixture.infrastructure.util.Constants.ACTIVE;

@Service
public class CreateCountryUseCaseImpl implements CreateCountryUseCase {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Map<String, String> createCountry(List<CountryRequest> countryRequest) {
        Map<String, String> response = new HashMap<>();
        Gson gson = new Gson();
        List<CountriesDocument> countriesDocuments = countryRequest.stream().map(country -> CountriesDocument.builder()
                .name(country.getName())
                .confederation(country.getConfederation())
                .continent(country.getContinent())
                .status(ACTIVE)
                .build()).collect(Collectors.toList());
        response.put("sizeRequest", String.valueOf(countriesDocuments.size()));

        List<CountriesDocument> countryFound = countryRepository.findByStatus("1");

        if (!countryFound.isEmpty()) {
            countriesDocuments.removeIf(first -> countryFound.stream().anyMatch(second -> second.getName().equals(first.getName())));
        }
        response.put("sizeClean", String.valueOf(countriesDocuments.size()));

        countriesDocuments.forEach(element -> {
            countryRepository.insert(element);
        });

        List<CountryDto> countryDtoList = countriesDocuments.stream()
                .map(document -> new ModelMapper().map(document, CountryDto.class))
                .collect(Collectors.toList());
        response.put("dto", gson.toJson(countryDtoList));

        return response;
    }
}
