package com.team.project.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HousingExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public void handleException(Exception e){
        e.printStackTrace();
    }
}
