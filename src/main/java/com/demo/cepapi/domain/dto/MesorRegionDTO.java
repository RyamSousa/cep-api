package com.demo.cepapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MesorRegionDTO {

    @JsonProperty("UF")
    private UFDTO uf;
}
