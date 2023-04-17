package com.team.project.service;


import com.team.project.domain.response.EmployeeApplicationResponse;
import com.team.project.entity.Application;
import com.team.project.entity.Employee;
import com.team.project.service.remote.RemoteApplicationService;
import com.team.project.service.remote.RemoteEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositeService {

    private RemoteApplicationService applicationService;
    private RemoteEmployeeService employeeService;

    @Autowired
    public void setApplicationService(RemoteApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Autowired
    public void setUserService(RemoteEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeeApplicationResponse getAllEmployeeAndApplication(){
        return EmployeeApplicationResponse.builder()
                .employees(employeeService.getAllEmployees())
                .applications(applicationService.getAllApplications())
                .build();
    }

    public List<Application> getAllApplication(){
        return applicationService.getAllApplications();
    }

    public List<Application> getApplicationsByStatus(String status){
        return applicationService.getApplicationsByStatus(status);
    }

    public Employee getEmployeeByUserId(Integer userId){
        return employeeService.findEmployeeByUserID(userId);
    }

//    public EmployeeHousingResponse getAllHousesAndEmployees(){
//        return EmployeeHousingResponse.builder()
//                .employees(employeeService.getAllUsers())
//                .houses(housingService.getHouse())
//                .build();
//    }



}
