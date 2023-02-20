package com.demo.cepapi.service;

import com.demo.cepapi.client.IBGEClient;
import com.demo.cepapi.client.ViaCepClient;
import com.demo.cepapi.config.FreightProperties;
import com.demo.cepapi.domain.dto.AddressResponseDTO;
import com.demo.cepapi.domain.dto.IbgeResponseDTO;
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

    @Autowired
    private IBGEClient ibgeClient;

    @Autowired
    private FreightProperties freightProperties;

    public ResponseEntity<AddressResponse> getAdressByCep(String cep) {
        if(!CepUtils.validCep(cep)){
            log.info("Invalid cep: {}", cep);
            return ResponseEntity.badRequest().build();
        }

        try {
            AddressResponseDTO addressByCep = viaCepClient.getAddressByCep(cep);
            log.info("::::: {}", addressByCep.getIbge());
            IbgeResponseDTO regionByUF = ibgeClient.getRegionByUF(addressByCep.getIbge());
            log.info("::::: {}", regionByUF.getMicroregion().getMesoregion().getUf().getRegion().getName());
            String freight = calculateFreight(regionByUF.getMicroregion().getMesoregion().getUf().getRegion().getName());

            return ResponseEntity.ok(AddressResponse.build(addressByCep.getCep(),
                    addressByCep.getLogradouro(),
                    addressByCep.getComplemento(),
                    addressByCep.getBairro(),
                    addressByCep.getLocalidade(),
                    addressByCep.getUf(),
                    String.format("R$ %s", freight)));

        }catch (FeignException e){
            log.info(e.getMessage(), e.responseBody(), e.request());
        }

        return ResponseEntity.badRequest().build();
    }

    private String calculateFreight(String name){
        return  freightProperties.getFreightValues().get(name);
    }
}
