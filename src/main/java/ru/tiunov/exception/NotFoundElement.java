package ru.tiunov.exception;

public class NotFoundElement extends Exception{
    public NotFoundElement() {
    }

    public NotFoundElement(String message) {
        super(message);
    }
}
