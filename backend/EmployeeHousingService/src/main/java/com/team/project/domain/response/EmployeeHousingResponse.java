package com.team.project.domain.response;

import com.team.project.entity.Employee;
import com.team.project.entity.House;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeHousingResponse {
    private String message;
    private List<Employee> employees;
    private List<House> houses;
}
