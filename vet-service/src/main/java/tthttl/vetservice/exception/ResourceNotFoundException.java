package tthttl.vetservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resourceType;

    public ResourceNotFoundException(String message, String resourceType) {
        super(message);
        this.resourceType = resourceType;
    }
}
