package com.demo.cepapi.domain.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AddressRequest {

    @NotBlank(message = "The CEP is required")
    private String cep;
}
