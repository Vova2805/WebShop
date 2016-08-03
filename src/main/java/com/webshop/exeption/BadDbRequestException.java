package com.webshop.exeption;


public class BadDbRequestException extends Exception {

    String message;

    public BadDbRequestException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param chars
     */
    public BadDbRequestException(String chars) {

        this.message = "There is no record responding following characteristics : " + chars;
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}
