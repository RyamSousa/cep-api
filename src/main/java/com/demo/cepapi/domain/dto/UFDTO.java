package com.demo.cepapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "build")
public class UFDTO {

    @JsonProperty("regiao")
    private RegionDTO region;
}
