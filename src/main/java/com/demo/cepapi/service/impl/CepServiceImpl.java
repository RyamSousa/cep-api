package com.demo.cepapi.service.impl;

import com.demo.cepapi.client.IBGEClient;
import com.demo.cepapi.client.ViaCepClient;
import com.demo.cepapi.config.FreightProperties;
import com.demo.cepapi.domain.dto.AddressResponseDTO;
import com.demo.cepapi.domain.dto.IbgeResponseDTO;
import com.demo.cepapi.domain.request.AddressRequest;
import com.demo.cepapi.domain.response.AddressResponse;
import com.demo.cepapi.exception.CepException;
import com.demo.cepapi.service.CepService;
import com.demo.cepapi.util.CepUtils;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CepServiceImpl implements CepService {

    @Autowired
    private ViaCepClient viaCepClient;

    @Autowired
    private IBGEClient ibgeClient;

    @Autowired
    private FreightProperties freightProperties;

    public ResponseEntity<AddressResponse> getAdressByCep(AddressRequest request) throws CepException {
        if (request == null || request.getCep() == null || request.getCep().isBlank()
                || !CepUtils.validCep(request.getCep())) {
            log.info("Invalid request: {}", request);
            throw new CepException("Informe o CEP", "");
        }

        try {
            AddressResponseDTO addressByCep = viaCepClient.getAddressByCep(request.getCep());

            if (addressByCep.getErro() != null && addressByCep.getErro().equals("true")){
                throw new CepException("CEP não encontrado", request.getCep());
            }

            IbgeResponseDTO regionByUF = ibgeClient.getRegionByUF(addressByCep.getIbge());
            String freight = calculateFreight(regionByUF.getMicroregion().getMesoregion().getUf().getRegion().getName());

            return ResponseEntity.ok(AddressResponse.build(addressByCep.getCep(),
                    addressByCep.getLogradouro(),
                    addressByCep.getComplemento(),
                    addressByCep.getBairro(),
                    addressByCep.getLocalidade(),
                    addressByCep.getUf(),
                    String.format(freight)));

        } catch (FeignException e) {
            if (e.status() == 404){
                throw new CepException("CEP não encontrado", request.getCep());
            }
            throw new CepException("Ocorreu um problema no nosso serviço, tente novamente mais tarde",
                    request.getCep());
        }
    }

    private String calculateFreight(String name) {
        return freightProperties.getFreightValues().get(name);
    }
}
