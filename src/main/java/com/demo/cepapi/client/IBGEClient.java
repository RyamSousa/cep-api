package com.demo.cepapi.client;

import com.demo.cepapi.domain.dto.IbgeResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "IBGEClient", url = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/")
public interface IBGEClient {

    @GetMapping(value = "{uf}")
    IbgeResponseDTO getRegionByUF(@PathVariable("uf") String uf);
}
