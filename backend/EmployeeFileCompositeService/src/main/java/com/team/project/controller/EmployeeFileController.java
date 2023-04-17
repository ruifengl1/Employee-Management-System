package com.team.project.controller;

import com.team.project.exception.UploadFailedException;
import com.team.project.service.EmployeeFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@RestController
public class EmployeeFileController {
    private EmployeeFileService employeeFileService;

    @Autowired
    public void setEmployeeFileServiceService(EmployeeFileService employeeFileService) {
        this.employeeFileService = employeeFileService;
    }

    @PostMapping("upload/visa-document") // not sure how to fix the bug: request is not multipart request ???
    public ResponseEntity<String> uploadEmployeeFile(@RequestParam("fileName") String fileName,
                                                     @RequestParam("file") MultipartFile file,
                                                     @RequestParam("userId") Integer userId,
                                                     @RequestParam("visaType") String visaType) throws UploadFailedException {
        return new ResponseEntity<>(employeeFileService.uploadEmployeeFile(file, fileName, userId, visaType), HttpStatus.OK);
    }

    @PutMapping("update/{userId}/visa-document")
    public String updateEmployeeVisaDocument(@PathVariable Integer userId,
                                               @RequestParam String visaType,
                                                @RequestParam String filePath){
        return employeeFileService.updateEmployeeFile(userId, visaType, new Timestamp(System.currentTimeMillis()), filePath);
    }

    @GetMapping("test")
    public String getTest(){
        return "tTEst tset";
    }

}
