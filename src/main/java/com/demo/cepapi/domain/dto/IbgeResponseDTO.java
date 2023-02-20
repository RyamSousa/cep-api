package com.demo.cepapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "build")
public class IbgeResponseDTO {

    @JsonProperty("microrregiao")
    private MicroRegionDTO microregion;

    @JsonIgnore
    private String imediateregion;
}
