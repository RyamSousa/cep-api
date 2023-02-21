package com.demo.cepapi.exception.handler;

import com.demo.cepapi.exception.CepException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class CepHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CepException.class})
    public ResponseEntity<?> handleNotFoundException(CepException exception){
        return new ResponseEntity<>(
                Map.of(
                        "error",exception.getMessage(),
                        "cep", exception.getCep()),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }
}
