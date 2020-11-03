package com.ie.stockapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find any entity with given id.")
@Slf4j
public class ResourceNotFoundException extends Exception {

    private final static String exceptionMessage = "Could not find any record with given id : ";

    public ResourceNotFoundException(String message) {
        super(String.join(exceptionMessage, message));
    }
}

