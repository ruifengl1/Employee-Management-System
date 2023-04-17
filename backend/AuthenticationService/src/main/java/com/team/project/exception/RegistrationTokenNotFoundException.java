package com.team.project.exception;

public class RegistrationTokenNotFoundException extends Exception {
    public RegistrationTokenNotFoundException() {
        super("Registration token not found.");
    }
    public RegistrationTokenNotFoundException(String s) {
        super(s);
    }
}
