package com.team.project.aop;

import com.team.project.domain.response.ErrorResponse;
import com.team.project.exception.BadCredentialsException;
import com.team.project.exception.RegistrationTokenNotFoundException;
import com.team.project.exception.UserAlreadyExistsException;
import com.team.project.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {BadCredentialsException.class, AuthenticationException.class})
    public ResponseEntity handleBadCredentialsException(Exception e){
        return new ResponseEntity(ErrorResponse.buildErrorResponse(
                e.getMessage()),
                HttpStatus.NON_AUTHORITATIVE_INFORMATION
        );

    }
    /*@ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleException(Exception e){
        return new ResponseEntity(ErrorResponse.buildErrorResponse(e.toString()), HttpStatus.OK);
    }*/

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity handleUserAlreadyExistsException(Exception e){
        return new ResponseEntity(ErrorResponse.buildErrorResponse(
                e.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(value = {RegistrationTokenNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity handleTokenNotFoundException(Exception e){
        return new ResponseEntity(ErrorResponse.buildErrorResponse(
                e.getMessage()),
                HttpStatus.OK
        );
    }

}
