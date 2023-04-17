package com.team.project.domain.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginBody {
    private String token;
    private Boolean isAdmin;
    private Integer userId;
}
