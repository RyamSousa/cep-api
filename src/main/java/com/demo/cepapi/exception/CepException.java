package com.demo.cepapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CepException extends RuntimeException {

    private String cep;

    public CepException(String message, String cep) {
        super(message);
        this.cep = cep;
    }
}
