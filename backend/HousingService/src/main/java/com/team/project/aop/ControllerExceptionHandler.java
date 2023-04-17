package com.team.project.aop;

import com.team.project.domain.response.ErrorResponse;
import com.team.project.exception.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {NoResultException.class, AuthenticationException.class})
    public ResponseEntity handleBadCredentialsException(Exception e){
        return new ResponseEntity(ErrorResponse.buildErrorResponse(
                e.getMessage()),
                HttpStatus.NON_AUTHORITATIVE_INFORMATION
        );

    }


}
