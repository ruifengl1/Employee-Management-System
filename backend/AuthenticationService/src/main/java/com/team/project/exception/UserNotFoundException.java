package com.team.project.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User not found.");
    }
    public UserNotFoundException(String s) {
        super(s);
    }
}
