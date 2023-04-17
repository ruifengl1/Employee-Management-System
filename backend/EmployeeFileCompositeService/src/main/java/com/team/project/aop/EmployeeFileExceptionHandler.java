package com.team.project.aop;


import com.team.project.exception.UploadFailedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class EmployeeFileExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(EmployeeFileExceptionHandler.class);
    @ExceptionHandler(value = {Exception.class})
    public void handleException(Exception e){
        e.printStackTrace();
    }

    @ExceptionHandler(value = {UploadFailedException.class})
    public void UploadFailedHandleException(UploadFailedException e){
        e.printStackTrace();
    }
}
