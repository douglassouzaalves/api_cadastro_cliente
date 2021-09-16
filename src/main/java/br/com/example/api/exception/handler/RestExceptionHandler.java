package br.com.example.api.exception.handler;


import br.com.example.api.exception.ResourceNotFoundDetails;
import br.com.example.api.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;


@ControllerAdvice //informando ao controller que é uma classe que possui informações que devem ser utilizadas
public class RestExceptionHandler extends Throwable {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
        ResourceNotFoundDetails rnfDetails;
        rnfDetails = ResourceNotFoundDetails.builder()
                .timestamp(LocalDate.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .details(rfnException.getMessage())
                .developerMessage(rfnException.getClass().getName())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> hlandeMethodNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
        ResourceNotFoundDetails errorDetails;
        errorDetails = ResourceNotFoundDetails.builder()
                .timestamp(LocalDate.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Erro no corpo da requisição")
                .details(methodArgumentNotValidException.getCause().getMessage())
                .developerMessage(methodArgumentNotValidException.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


}

