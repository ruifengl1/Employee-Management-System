package com.team.project.service.remote;

import com.team.project.entity.EmployeeService.Employee;
import com.team.project.entity.EmployeeService.VisaDocument;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("employee-service")
public interface RemoteEmployeeService {
    @GetMapping(value = "employee-service/employee/{userId}")
    Employee findEmployeeByUserID(@PathVariable Integer userId);

    @GetMapping(value = "employee-service/employee/{userId}/visa_document")
    List<VisaDocument> findEmployeeVisaStatusDocument(@PathVariable Integer userId);

    @PutMapping(value = "employee-service/employee/{userId}/visa_document")
    void updateEmployeeVisaDocument(@PathVariable Integer userId, @RequestBody Employee employee);
}
