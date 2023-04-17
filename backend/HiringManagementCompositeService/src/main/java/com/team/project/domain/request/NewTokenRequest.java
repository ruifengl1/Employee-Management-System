package com.team.project.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class NewTokenRequest {

    private String email;
    private Integer createBy;

}
