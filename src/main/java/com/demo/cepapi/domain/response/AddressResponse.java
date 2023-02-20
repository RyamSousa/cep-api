package com.demo.cepapi.domain.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressResponse {

    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String frete;
}
