package com.team.project.domain.response;

import com.team.project.entity.Application;
import com.team.project.entity.Employee;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeApplicationResponse {
    private List<Application> applications;
    private List<Employee> employees;
}
