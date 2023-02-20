package com.demo.cepapi.service;

import com.demo.cepapi.domain.response.AddressResponse;
import com.demo.cepapi.util.CepUtils;
import org.springframework.stereotype.Service;

@Service
public class CepService {
    public AddressResponse getAdressByCep(String cep) {
        CepUtils.validCep(cep);
        return new AddressResponse();
    }
}
