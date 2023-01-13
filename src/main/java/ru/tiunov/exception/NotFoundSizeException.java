package ru.tiunov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Size don't found")
public class NotFoundSizeException extends RuntimeException {
    public NotFoundSizeException() {
    }

    public NotFoundSizeException(String message) {
        super(message);
    }
}
