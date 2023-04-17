package com.team.project.exception;

public class NoResultException extends Exception {
    public NoResultException() {
        super("No Result.");
    }
    public NoResultException(String s) {
        super(s);
    }
}