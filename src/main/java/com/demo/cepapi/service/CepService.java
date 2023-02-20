package com.demo.cepapi.service;

import com.demo.cepapi.domain.response.AddressResponse;
import org.springframework.http.ResponseEntity;

public interface CepService {

    ResponseEntity<AddressResponse> getAdressByCep(String cep);
}
