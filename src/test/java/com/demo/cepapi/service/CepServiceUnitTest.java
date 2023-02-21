package com.demo.cepapi.service;

import com.demo.cepapi.client.IBGEClient;
import com.demo.cepapi.client.ViaCepClient;
import com.demo.cepapi.config.FreightProperties;
import com.demo.cepapi.domain.dto.*;
import com.demo.cepapi.domain.request.AddressRequest;
import com.demo.cepapi.domain.response.AddressResponse;
import com.demo.cepapi.exception.CepException;
import com.demo.cepapi.service.impl.CepServiceImpl;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import io.cucumber.messages.internal.com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CepServiceUnitTest {


    private static final String VALID_CEP = "59960-000";
    private static final String INVALID_CEP = "99999-999";
    private static final String INVALID_FORMAT_CEP = "999-999";
    private static final String STREET = "some street";
    private static final String COMPLEMENT = "some complement";
    private static final String NEIGHBORHOOD = "some neighborhood";
    private static final String CITY = "some city";
    private static final String STATE = "RN";
    private static final String IBGE_CODE = "20000";
    private static final String REGION = "Some Region";
    private static final String FREIGHT = "15.02";
    private static final String EXCEPTION_MESSAGE = "Exception message";

    @Mock
    ViaCepClient viaCepClient;

    @Mock
    IBGEClient ibgeClient;

    @Mock
    FreightProperties freightProperties;

    @InjectMocks
    CepServiceImpl cepService;

    AddressRequest addressRequest;

    @Test
    void givenCepWhenFormatCepIsValidThenGetAdressByCep() {
        addressRequest = AddressRequest.build(VALID_CEP);
        AddressResponseDTO addressdto = AddressResponseDTO.build(VALID_CEP, STREET, COMPLEMENT, NEIGHBORHOOD, CITY, STATE, IBGE_CODE, "");
        IbgeResponseDTO ibgeDto = IbgeResponseDTO.build(MicroRegionDTO.build(MesorRegionDTO.build(UFDTO.build(RegionDTO.build(REGION)))), "");

        given(viaCepClient.getAddressByCep(VALID_CEP)).willReturn(addressdto);
        given(ibgeClient.getRegionByUF(IBGE_CODE)).willReturn(ibgeDto);
        given(freightProperties.getFreightValues()).willReturn(ImmutableMap.of(REGION, FREIGHT));

        ResponseEntity<AddressResponse> response = cepService.getAddressByCep(addressRequest);

        assertNotNull(response);
        assertEquals(VALID_CEP, response.getBody().getCep());
        assertEquals(NEIGHBORHOOD, response.getBody().getBairro());
        assertEquals(FREIGHT, response.getBody().getFrete());
        assertEquals(STATE, response.getBody().getEstado());
        assertEquals(STREET, response.getBody().getRua());
        assertEquals(COMPLEMENT, response.getBody().getComplemento());
        assertEquals(CITY, response.getBody().getCidade());
    }

    @Test
    void givenCepWhenFormatCepIsInvalidThenThrowsException() {
        addressRequest = AddressRequest.build(INVALID_FORMAT_CEP);

        try {
            cepService.getAddressByCep(addressRequest);
        } catch (CepException e) {
            assertEquals("", e.getCep());
        }
    }

    @Test
    void givenCepWhenFormatCepIsValidButDontExistsThenThrowsException() {
        addressRequest = AddressRequest.build(INVALID_CEP);
        AddressResponseDTO addressdto = AddressResponseDTO.build("", "", "",
                "", "", "", "", "true");
        given(viaCepClient.getAddressByCep(INVALID_CEP)).willReturn(addressdto);

        try {
            cepService.getAddressByCep(addressRequest);
        } catch (CepException e) {
            assertEquals(INVALID_CEP, e.getCep());
        }
    }

    @Test
    void givenCepWhenFormatCepValidButTheServiceIsUnavailableThenThrowsException() {
        addressRequest = AddressRequest.build(INVALID_CEP);
        Request request = Request.create(Request.HttpMethod.POST, "url", new HashMap(), null, new RequestTemplate());
        FeignException.NotFound exception = new FeignException.NotFound("", request, new byte[0], new HashMap<>());
        given(viaCepClient.getAddressByCep(INVALID_CEP)).willThrow(exception);

        try {
            cepService.getAddressByCep(addressRequest);
        } catch (CepException e) {
            assertEquals(INVALID_CEP, e.getCep());
        }
    }
}