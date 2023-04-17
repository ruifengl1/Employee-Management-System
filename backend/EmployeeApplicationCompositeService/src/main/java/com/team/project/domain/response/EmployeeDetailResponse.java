package com.team.project.domain.response;

import com.team.project.entity.Employee;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDetailResponse {
    private List<Employee> employees;
    private String status;
}
