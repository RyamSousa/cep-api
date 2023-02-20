package com.demo.cepapi.client;

import com.demo.cepapi.domain.dto.AddressResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ViaCepClient", url = "${via.cep.api}")
public interface ViaCepClient {

    @GetMapping(value = "{cep}/json")
    AddressResponseDTO getAddressByCep(@PathVariable("cep") String cep);
}
