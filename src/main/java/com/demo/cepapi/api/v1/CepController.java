package com.demo.cepapi.api.v1;

import com.demo.cepapi.domain.request.AddressRequest;
import com.demo.cepapi.domain.response.AddressResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "CEP Rest API")
public interface CepController {

    @ApiOperation(value = "Calculate freight and return address data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return freight value and the address informations"),
            @ApiResponse(code = 404, message = "CEP not found")})
    @PostMapping(value = "/v1/consulta-endereco", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AddressResponse> getAddressByCep(@RequestBody AddressRequest addressRequest);
}
