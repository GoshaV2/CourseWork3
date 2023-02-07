package ru.tiunov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Don't find socks with this parameters")
public class NotFoundElement extends RuntimeException{
    public NotFoundElement() {
    }

    public NotFoundElement(String message) {
        super(message);
    }
}
