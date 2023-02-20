package com.demo.cepapi.service;

import com.demo.cepapi.client.ViaCepClient;
import com.demo.cepapi.domain.dto.AddressResponseDTO;
import com.demo.cepapi.domain.response.AddressResponse;
import com.demo.cepapi.util.CepUtils;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CepService {

    @Autowired
    private ViaCepClient viaCepClient;

    public ResponseEntity<AddressResponse> getAdressByCep(String cep) {
        if(!CepUtils.validCep(cep)){
            log.info("Invalid cep: {}", cep);
            return ResponseEntity.badRequest().build();
        }

        try {
            AddressResponseDTO addressByCep = viaCepClient.getAddressByCep(cep);

            return ResponseEntity.ok(AddressResponse.build(addressByCep.getCep(),
                    addressByCep.getLogradouro(),
                    addressByCep.getComplemento(),
                    addressByCep.getBairro(),
                    addressByCep.getLocalidade(),
                    addressByCep.getUf(),
                    "frete aqui"));

        }catch (FeignException e){

        }

        return ResponseEntity.badRequest().build();
    }
}
