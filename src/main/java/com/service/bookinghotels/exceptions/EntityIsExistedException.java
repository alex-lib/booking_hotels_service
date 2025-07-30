package com.service.bookinghotels.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityIsExistedException extends RuntimeException {

    public EntityIsExistedException(String message) {
        super(message);
    }
}