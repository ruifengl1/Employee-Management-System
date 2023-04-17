package com.team.project.domain.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmployeeSummaryViewResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String ssn;
    private String workAuthorizationTitle;
    private String phoneNumber;
    private String email;
}
