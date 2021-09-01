package br.com.example.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)

public class ResourceNotValidException extends Exception {

    public ResourceNotValidException(String message) {
        super(message);
    }
}
