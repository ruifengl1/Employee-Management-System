package com.team.project.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class EmployeeExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(EmployeeExceptionHandler.class);
    @ExceptionHandler(value = {Exception.class})
    public void handleException(Exception e){
        e.printStackTrace();
    }
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void UploadFailedHandleException(IllegalArgumentException e){
        e.printStackTrace();
    }
}
