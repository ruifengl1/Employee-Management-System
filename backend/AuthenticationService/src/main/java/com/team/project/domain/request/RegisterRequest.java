package com.team.project.domain.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@ToString
public class RegisterRequest {
    @NotNull
    @Size(min = 3, max = 20, message = "Username must be between 3 and " +
            "20 characters")
    private String username;
    @NotNull
    private String password;
    @NotNull
    @Email(message = "Email is not valid")
    private String email;

    @NotNull
    private Integer tokenId;

}
