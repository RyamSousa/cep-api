package com.demo.cepapi.api.v1.impl;

import com.demo.cepapi.api.v1.CepController;
import com.demo.cepapi.domain.request.AddressRequest;
import com.demo.cepapi.domain.response.AddressResponse;
import com.demo.cepapi.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CepControllerImpl implements CepController {

    @Autowired
    private CepService addressService;

    @Override
    public ResponseEntity<AddressResponse> getAddressByCep(AddressRequest addressRequest) {
        return addressService.getAddressByCep(addressRequest);
    }
}
