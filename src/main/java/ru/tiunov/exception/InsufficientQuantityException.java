package ru.tiunov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "You trying reduce more socks than we having")
public class InsufficientQuantityException extends RuntimeException{
    private final int gettingQuantity;
    private final int existingQuantity;

    public InsufficientQuantityException(int gettingQuantity, int existingQuantity) {
        this.gettingQuantity = gettingQuantity;
        this.existingQuantity = existingQuantity;
    }

    public InsufficientQuantityException(String message, int gettingQuantity, int existingQuantity) {
        super(message);
        this.gettingQuantity = gettingQuantity;
        this.existingQuantity = existingQuantity;
    }
}
