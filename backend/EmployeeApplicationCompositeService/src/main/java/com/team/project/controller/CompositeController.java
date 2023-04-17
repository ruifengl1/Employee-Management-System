package com.team.project.controller;

import com.team.project.domain.response.EmployeeApplicationResponse;
import com.team.project.domain.response.GeneralResponse;
import com.team.project.entity.Application;
import com.team.project.entity.Employee;
import com.team.project.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class CompositeController {

    private CompositeService compositeService;

    @Autowired
    public void setCompositeService(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @GetMapping("getEmployeeAndStatus")
    public GeneralResponse<EmployeeApplicationResponse> getEmployeeAndStatus(){
        EmployeeApplicationResponse employeeApplicationResponse = compositeService.getAllEmployeeAndApplication();
        return GeneralResponse.<EmployeeApplicationResponse>builder()
                .body(employeeApplicationResponse)
                .build();
    }



//    @GetMapping("/pending")
//    public List<Employee> getPendingEmployees() {
//        List<Application> applications = employeeRepository.findApplicationsByStatus("PENDING");
//        List<Integer> employeeIds = applications.stream()
//                .map(Application::getEmployeeId)
//                .distinct()
//                .collect(Collectors.toList());
//        List<Employee> employees = new ArrayList<>();
//        for (Integer id : employeeIds) {
//            Employee employee = employeeRepository.findById(id);
//            if (employee != null) {
//                employees.add(employee);
//            }
//        }
//        return employees;
//    }

    @GetMapping("/pending")
    public List<Employee> getPendingEmployees() {
        List<Application> applications = compositeService.getApplicationsByStatus("PENDING");
        List<Integer> employeeIds = applications.stream()
                .map(Application::getEmployeeId)
                .distinct()
                .collect(Collectors.toList());
        List<Employee> employees = new ArrayList<>();
        for (Integer id : employeeIds) {
            Employee employee = compositeService.getEmployeeByUserId(id);
            if (employee != null) {
                employees.add(employee);
            }
        }
        return employees;
    }

    @GetMapping("/approved")
    public List<Employee> getApprovedEmployees() {
        List<Application> applications = compositeService.getApplicationsByStatus("APPROVED");
        List<Integer> employeeIds = applications.stream()
                .map(Application::getEmployeeId)
                .distinct()
                .collect(Collectors.toList());
        List<Employee> employees = new ArrayList<>();
        for (Integer id : employeeIds) {
            Employee employee = compositeService.getEmployeeByUserId(id);
            if (employee != null) {
                employees.add(employee);
            }
        }
        return employees;
    }

    @GetMapping("/rejected")
    public List<Employee> getRejectedEmployees() {
        List<Application> applications = compositeService.getApplicationsByStatus("REJECTED");
        List<Integer> employeeIds = applications.stream()
                .map(Application::getEmployeeId)
                .distinct()
                .collect(Collectors.toList());
        List<Employee> employees = new ArrayList<>();
        for (Integer id : employeeIds) {
            Employee employee = compositeService.getEmployeeByUserId(id);
            if (employee != null) {
                employees.add(employee);
            }
        }
        return employees;
    }

}
