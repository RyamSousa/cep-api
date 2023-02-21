package com.demo.cepapi.service;

import com.demo.cepapi.domain.request.AddressRequest;
import com.demo.cepapi.domain.response.AddressResponse;
import com.demo.cepapi.exception.CepException;
import org.springframework.http.ResponseEntity;

public interface CepService {

    ResponseEntity<AddressResponse> getAddressByCep(AddressRequest request) throws CepException;
}
