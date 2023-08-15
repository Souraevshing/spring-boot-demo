package com.spring.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    private static  final long serialVersionID = 1;

    public  NotFoundException(String err) {
        super(err);
    }
}
