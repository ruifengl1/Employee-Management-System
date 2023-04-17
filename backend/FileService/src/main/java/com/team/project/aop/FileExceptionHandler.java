package com.team.project.aop;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@ControllerAdvice
public class FileExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(FileExceptionHandler.class);
    @ExceptionHandler(value = {Exception.class})
    public void handleException(Exception e){
        e.printStackTrace();
    }
    @ExceptionHandler(value = {IOException.class})
    public void handleIOException(IOException e){
        logger.error("IOException: " + e.getMessage());
    }
    @ExceptionHandler(value = {AmazonServiceException.class})
    public void handleAmazonServiceException(AmazonServiceException e){
        logger.info("AmazonServiceException: "+ e.getMessage());
    }
    @ExceptionHandler(value = {AmazonClientException.class})
    public void handleAmazonClientException(AmazonClientException e){
        logger.info("AmazonClientException Message: " + e.getMessage());
    }
}
