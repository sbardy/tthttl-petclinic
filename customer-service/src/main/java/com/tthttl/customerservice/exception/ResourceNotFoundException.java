package com.tthttl.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceType;

    public ResourceNotFoundException(String message, String resourceType) {
        super(message);
        this.resourceType = resourceType;
    }

}
