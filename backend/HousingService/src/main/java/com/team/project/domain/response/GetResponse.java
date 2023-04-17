package com.team.project.domain.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetResponse<T> {
    private String message;
    private List<T> result;
}