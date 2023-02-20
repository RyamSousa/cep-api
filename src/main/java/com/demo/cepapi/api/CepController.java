package com.demo.cepapi.api;

import com.demo.cepapi.domain.request.AddressRequest;
import com.demo.cepapi.domain.response.AddressResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface CepController {

    @PostMapping(value = "/v1/consulta-endereco", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AddressResponse> getAddressByCep(@RequestBody AddressRequest addressRequest);
}
