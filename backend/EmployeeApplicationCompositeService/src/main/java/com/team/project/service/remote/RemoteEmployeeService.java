package com.team.project.service.remote;

import com.team.project.entity.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("employee-service")
public interface RemoteEmployeeService {

    @GetMapping("employee-service/employee/all")
    List<Employee> getAllEmployees();

    @GetMapping( "employee-service/employee/{userId}")
    public Employee findEmployeeByUserID(@PathVariable Integer userId);
}
