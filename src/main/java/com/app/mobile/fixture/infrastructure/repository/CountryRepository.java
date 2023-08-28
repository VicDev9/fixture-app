package com.app.mobile.fixture.infrastructure.repository;


import com.app.mobile.fixture.domain.document.CountriesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends MongoRepository<CountriesDocument, String> {

    List<CountriesDocument> findByStatus(String status);

    Optional<CountriesDocument> findByName(String name);

    Optional<CountriesDocument> findByNameAndStatus(String name, String status);

    Optional<CountriesDocument> findByIdAndStatus(String id, String status);


}
