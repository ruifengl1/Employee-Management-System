package com.team.project.service;

import com.team.project.entity.EmployeeService.Employee;
import com.team.project.entity.EmployeeService.PersonalDocument;
import com.team.project.entity.EmployeeService.VisaStatus;
import com.team.project.exception.UploadFailedException;
import com.team.project.service.remote.RemoteFileService;
import com.team.project.service.remote.RemoteEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Service
public class EmployeeFileService {
    final private RemoteEmployeeService employeeService;
    final private RemoteFileService fileService;

    @Autowired
    public EmployeeFileService(RemoteEmployeeService employeeService, RemoteFileService applicationService) {
        this.employeeService = employeeService;
        this.fileService = applicationService;
    }

    public String uploadEmployeeFile(MultipartFile file, String fileName, int userId, String visaType) throws UploadFailedException {
        // upload file to the specific s3 bucket
        ResponseEntity<String> fileServiceResponse = fileService.uploadFile(fileName, file);
        if(fileServiceResponse.getStatusCode()!= HttpStatus.OK){
            throw new UploadFailedException(fileName + " fail uploaded");
        }
        String filePath = fileServiceResponse.getBody();
        Timestamp ts = new Timestamp(System.currentTimeMillis());

        updateEmployeeFile(userId, visaType, ts, filePath);

        return "File uploaded successfully";
    }

    public String updateEmployeeFile(int userId, String visaType, Timestamp ts, String filePath) {
        // get the employee object
        Employee employee = employeeService.findEmployeeByUserID(userId);
        System.out.println(employee);
        // update the user's visa and personal document record
        VisaStatus visaStatus = VisaStatus.builder()
                .visaType(visaType)
                .activeFlag("PENDING")
                .startDate(ts)
                .endDate(null)
                .lastModificationDate(ts)
                .build();

        PersonalDocument personalDocument = PersonalDocument.builder()
                .title(visaType)
                .documentType("visa")
                .comment(null)
                .createDate(ts)
                .path(filePath)
                .build();
        employee.getVisaStatuses().add(visaStatus);
        employee.getPersonalDocuments().add(personalDocument);
        employeeService.updateEmployeeVisaDocument(userId, employee);

        return "Status updated successfully";
    }



}
