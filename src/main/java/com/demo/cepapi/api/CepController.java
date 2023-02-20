package com.demo.cepapi.api;

import com.demo.cepapi.domain.response.AddressResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface CepController {

    @GetMapping(value = "/cep/{cep}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AddressResponse> getAddressByCep(@PathVariable("cep") String cep);
}
