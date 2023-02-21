package com.demo.cepapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "build")
public class MicroRegionDTO {

    @JsonProperty("mesorregiao")
    private MesorRegionDTO mesoregion;
}
