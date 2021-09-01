package br.com.example.api.exception.handler;


import br.com.example.api.exception.ResourceNotFoundDetails;
import br.com.example.api.exception.ResourceNotFoundException;
import br.com.example.api.exception.ResourceNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


@ControllerAdvice //informando ao controller que é uma classe que possui informações que devem ser utilizadas
public class RestExceptionHandler extends Throwable {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
        ResourceNotFoundDetails rnfDetails;
        rnfDetails = ResourceNotFoundDetails.ResourceNotFoundDetailsBuilder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .details(rfnException.getMessage())
                .developerMessage(rfnException.getClass().getName())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(ResourceNotValidException.class)
//    public ResponseEntity<?> handleResourceBadRequest(ResourceNotValidException notValidException) {
//        ResourceNotFoundDetails rnfDetails;
//        rnfDetails = ResourceNotFoundDetails.ResourceNotFoundDetailsBuilder.newBuilder()
//                .timestamp(new Date().getTime())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .title("Error... Not Found!")
//                .details(notValidException.getMessage())
//                .developerMessage(notValidException.getClass().getName())
//                .build();
//        return new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> hlandeHttpMessageNotReadable(HttpMessageNotReadableException httpMessageNotReadableException) {
        ResourceNotFoundDetails errorDetails;
        errorDetails = ResourceNotFoundDetails.ResourceNotFoundDetailsBuilder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Erro no corpo da requisição")
                .details(httpMessageNotReadableException.getCause().getMessage())
                .developerMessage(httpMessageNotReadableException.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


}

