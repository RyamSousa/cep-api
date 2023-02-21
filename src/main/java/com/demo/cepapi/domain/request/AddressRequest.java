package com.demo.cepapi.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor(staticName = "build")
public class AddressRequest {

    @NotBlank(message = "The CEP is required")
    private String cep;
}
