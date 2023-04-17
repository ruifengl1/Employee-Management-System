package com.team.project.exception;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("Username or email already exists, please try again.");
    }
    public UserAlreadyExistsException(String s) {
        super(s);
    }
}
