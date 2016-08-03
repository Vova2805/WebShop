package com.webshop.exeption;


public class UserAlreadyExistsException extends Exception {

    String message;

    public UserAlreadyExistsException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param username is name of existed user
     */
    public UserAlreadyExistsException(String username) {
        this.message = "User with name : " + username + " already exists";
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
