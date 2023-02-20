package com.demo.cepapi.api.impl;

import com.demo.cepapi.api.CepController;
import com.demo.cepapi.domain.response.AddressResponse;
import com.demo.cepapi.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CepControllerImpl implements CepController {

    @Autowired
    private CepService addressService;

    @Override
    public ResponseEntity<AddressResponse> getAddressByCep(String cep) {
        return addressService.getAdressByCep(cep);
    }
}
