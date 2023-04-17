package com.team.project.domain.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmployeeStatusResponse {
    private String name;
    private List<WorkAuthorizationResponse> workAuthorizationResponses;

}
