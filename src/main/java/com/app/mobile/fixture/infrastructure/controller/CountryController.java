package com.app.mobile.fixture.infrastructure.controller;

import com.app.mobile.fixture.application.business.CreateCountryUseCase;
import com.app.mobile.fixture.application.business.DeleteCountryUseCase;
import com.app.mobile.fixture.application.business.ReadCountryUseCase;
import com.app.mobile.fixture.application.business.UpdateCountryUseCase;
import com.app.mobile.fixture.domain.dto.CountryDto;
import com.app.mobile.fixture.domain.request.CountryRequest;
import com.app.mobile.fixture.domain.request.CountryUpdateRequest;
import com.app.mobile.fixture.domain.response.CountryListResponse;
import com.app.mobile.fixture.domain.response.CountryResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CreateCountryUseCase createCountryUseCase;

    @Autowired
    private ReadCountryUseCase readCountryUseCase;

    @Autowired
    private UpdateCountryUseCase updateCountryUseCase;

    @Autowired
    private DeleteCountryUseCase deleteCountryUseCase;

    private Gson gson = new Gson();

    @PostMapping("/create")
    public ResponseEntity<CountryListResponse> insertCountries(@Valid @RequestBody List<CountryRequest> countryRequestList) {
        Map<String, String> response = createCountryUseCase.createCountry(countryRequestList);
        String message = response.get("sizeClean").concat(" of ").concat(response.get("sizeRequest")).concat(" were added.");
        if (!response.get("sizeRequest").equals(response.get("sizeClean"))) {
            message = message.concat("The others weren't added already exist");
        }
        Type typeList = new TypeToken<List<CountryDto>>() {
        }.getType();
        CountryListResponse countryListResponse = CountryListResponse.builder()
                .message(message)
                .countryList(gson.fromJson(response.get("dto"), typeList))
                .build();
        return new ResponseEntity<>(countryListResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<CountryResponse> findOneCountry(@Valid @PathVariable String name) {
       CountryDto countryDto = readCountryUseCase.findCountry(name);
       String message = "Country found.";
       CountryResponse countryResponse = CountryResponse.builder()
               .countryDto(countryDto)
               .message(message)
               .build();
       return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<CountryListResponse> findAllCountry() {
        CountryListResponse countryListResponse = CountryListResponse.builder()
                .message("Countries' list")
                .countryList(readCountryUseCase.findAllCountries())
                .build();
        return new ResponseEntity<>(countryListResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CountryResponse> updateCountry(@Valid @RequestBody CountryUpdateRequest countryUpdateRequest) {
        CountryResponse countryResponse = CountryResponse.builder()
                .message("Country updated")
                .countryDto(updateCountryUseCase.updateCountry(countryUpdateRequest))
                .build();
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CountryResponse> deleteCountry(@Valid @RequestParam(defaultValue = "id") String id) {
        CountryResponse countryResponse = CountryResponse.builder()
                .message("Country deleted")
                .countryDto(deleteCountryUseCase.deleteCountry(id))
                .build();
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }
}
