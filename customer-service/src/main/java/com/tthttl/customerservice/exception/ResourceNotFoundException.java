package com.tthttl.customerservice.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final String resourceType;

    public ResourceNotFoundException(String message, String resourceType) {
        super(message);
        this.resourceType = resourceType;
    }

}
