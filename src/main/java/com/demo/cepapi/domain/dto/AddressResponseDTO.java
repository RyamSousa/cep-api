package com.demo.cepapi.domain.dto;

import lombok.Getter;

@Getter
public class AddressResponseDTO {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
}
