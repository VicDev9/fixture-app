package com.app.mobile.fixture.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CountryUpdateRequest {

    private String id;
    private String name;
    private String continent;
    private String confederation;

}
