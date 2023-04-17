package com.team.project.exception;

public class BadCredentialsException extends Exception {
    public BadCredentialsException() {
        super("Incorrect credentials, please try again.");
    }
    public BadCredentialsException(String s) {
        super(s);
    }
}
