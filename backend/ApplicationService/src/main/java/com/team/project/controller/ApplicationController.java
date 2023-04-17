package com.team.project.controller;

import com.team.project.entity.Application;
import com.team.project.entity.Document;
import com.team.project.service.ApplicationService;
import com.team.project.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("application")
public class ApplicationController {

    private ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @GetMapping
    public List<Application> getAllApplications(){
        return applicationService.getAllApplications();
    }

    @GetMapping("{id}")
    public Application getApplicationById(@PathVariable int id){
        return applicationService.getApplicationById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public Application getApplicationByEmployeeId(@PathVariable int employeeId){
        return applicationService.getApplicationByEmployeeId(employeeId);
    }

    @PatchMapping("/{employeeId}/status")
    public ResponseEntity<String> updateApplicationStatusByEmployeeId(@PathVariable("employeeId") Integer employeeId, @RequestBody Map<String, String> requestBody) {
        String newStatus = requestBody.get("status");
        if (newStatus == null) {
            return ResponseEntity.badRequest().body("Missing status in request body");
        }

        try {
            applicationService.updateApplicationStatus(employeeId, newStatus);
            return ResponseEntity.ok("Application status updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating application status.");
        }
    }

    @GetMapping("/status/{status}")
    public List<Application> getApplicationsByStatus(@PathVariable String status){
        return applicationService.getApplicationsByStatus(status);
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<String> updateApplication(@PathVariable int employeeId, @RequestBody Application updatedApplication) {
        Application application = applicationService.getApplicationByEmployeeId(employeeId);
        if (application == null) {
            return ResponseEntity.notFound().build();
        }
        if (updatedApplication.getCreateDate() != null) {
            application.setCreateDate(updatedApplication.getCreateDate());
        }
        if (updatedApplication.getLastModificationDate() != null) {
            application.setLastModificationDate(updatedApplication.getLastModificationDate());
        }
        if (updatedApplication.getStatus() != null) {
            application.setStatus(updatedApplication.getStatus());
        }
        if (updatedApplication.getComment() != null) {
            application.setComment(updatedApplication.getComment());
        }
        try {
            applicationService.updateApplication(application);
            return ResponseEntity.ok("Application updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating application.");
        }
    }

    @PostMapping("")
    public ResponseEntity<String> createApplication(@RequestBody Application application) {
        try {
            applicationService.createApplication(application);
            return ResponseEntity.ok("Application created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating application.");
        }
    }



}
