package com.tthttl.visitservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    String entityType;

    public ResourceNotFoundException(String message, String entityType) {
        super(message);
        this.entityType = entityType;
    }
}
