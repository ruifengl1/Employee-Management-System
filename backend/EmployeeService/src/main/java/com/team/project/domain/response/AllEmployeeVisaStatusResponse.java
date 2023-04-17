package com.team.project.domain.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AllEmployeeVisaStatusResponse {
    private List<EmployeeStatusResponse> allStatus;

}
