package com.webshop.exeption;


public class UserNotFoundException extends Exception {

    String message;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
