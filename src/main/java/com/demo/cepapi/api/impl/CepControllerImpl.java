package com.demo.cepapi.api.impl;

import com.demo.cepapi.api.CepController;
import com.demo.cepapi.domain.request.AddressRequest;
import com.demo.cepapi.domain.response.AddressResponse;
import com.demo.cepapi.service.impl.CepServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CepControllerImpl implements CepController {

    @Autowired
    private CepServiceImpl addressService;

    @Override
    public ResponseEntity<AddressResponse> getAddressByCep(AddressRequest addressRequest) {
        return addressService.getAdressByCep(addressRequest);
    }
}
