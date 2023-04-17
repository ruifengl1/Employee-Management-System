package com.team.project.domain.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@ToString
public class NewTokenRequest {

    @NotNull
    @Email(message = "Email is not valid")
    private String email;

    @NotNull
    private Integer createBy;

}
