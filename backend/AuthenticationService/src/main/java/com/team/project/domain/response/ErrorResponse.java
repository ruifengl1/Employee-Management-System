package com.team.project.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Setter
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL) // exclude null fields
public class ErrorResponse {
    ResponseStatus status;
     public static ErrorResponse buildErrorResponse(String msg) {
         return ErrorResponse.builder()
                 .status(
                         ResponseStatus.builder()
                                 .success(false)
                                 .message(msg)
                                 .build()
                 )
                 .build();
    }
    public static ErrorResponse buildErrorResponse( List<FieldError> errors) {
         StringBuilder s = new StringBuilder();
        errors.forEach(error -> {
           s.append(error.getObjectName() + ": " + error.getDefaultMessage() + " ");
        });
        return ErrorResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(false)
                                .message(s.toString())
                                .build()
                )
                .build();
    }
}
