package com.app.mobile.fixture.domain.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "countries")
public class CountriesDocument {

    @Id
    private String id;
    private String name;
    private String continent;
    private String confederation;
    private String status;

}
