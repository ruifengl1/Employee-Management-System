package com.team.project.controller;

import com.team.project.domain.response.AllEmployeeVisaStatusResponse;
import com.team.project.domain.response.EmployeeSummaryViewResponse;
import com.team.project.domain.response.VisaStatusManagementResponse;
import com.team.project.entity.*;
import com.team.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String getEmployee(){
        return "Employee Service";
    }

    @GetMapping(value = "/all")
    public List<Employee> findAllEmployees() {
        return service.findAllEmployees();
    }
    @GetMapping(value = "/all/{houseId}")
    public List<Employee> findEmployeeByHouseId(@PathVariable Integer houseId){return service.findEmployeeByHouseId(houseId);}
    @GetMapping(value = "/{userId}")
    public Employee findEmployeeByUserID(@PathVariable Integer userId) {
        return service.findEmployeeByUserID(userId);
    }
    @GetMapping(value = "/{userId}/visa_status")
    public List<VisaStatus> findEmployeeVisaStatus(@PathVariable Integer userId) {
        return service.findEmployeeVisaStatus(userId);
    }
    @GetMapping(value = "/{userId}/personal_document")
    public List<PersonalDocument> findEmployeePersonalDocument(@PathVariable Integer userId) {
        return service.findEmployeePersonalDocument(userId);
    }

    @GetMapping(value = "/{userId}/visa_document")
    public List<VisaDocument> findEmployeeVisaStatusDocument(@PathVariable Integer userId) {
        return service.findEmployeeVisaStatusDocument(userId);
    }
    @PutMapping(value = "/{userId}/visa_document")
    public void updateEmployeeVisaDocument(@PathVariable Integer userId, @RequestBody Employee employee) {
        service.updateEmployeeVisaDocument(userId, employee);
    }

    // Create a new employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = service.saveEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    // Update employee information by userId
    @PatchMapping("/{userId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("userId") Integer userId, @RequestBody Employee employee) {
        Employee existingEmployee = service.findEmployeeByUserID(userId);
        if (existingEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (employee.getUserID() != null) {
            existingEmployee.setUserID(employee.getUserID());
        }

        if (employee.getFirstName() != null) {
            existingEmployee.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null) {
            existingEmployee.setLastName(employee.getLastName());
        }
        if (employee.getMiddleName() != null) {
            existingEmployee.setMiddleName(employee.getMiddleName());
        }
        if (employee.getPreferredName() != null) {
            existingEmployee.setPreferredName(employee.getPreferredName());
        }
        if (employee.getEmail() != null) {
            existingEmployee.setEmail(employee.getEmail());
        }
        if (employee.getCellPhone() != null) {
            existingEmployee.setCellPhone(employee.getCellPhone());
        }
        if (employee.getAlternatePhone() != null) {
            existingEmployee.setAlternatePhone(employee.getAlternatePhone());
        }
        if (employee.getGender() != null) {
            existingEmployee.setGender(employee.getGender());
        }
        if (employee.getSsn() != null) {
            existingEmployee.setSsn(employee.getSsn());
        }
        if (employee.getDob() != null) {
            existingEmployee.setDob(employee.getDob());
        }
        if (employee.getStartDate() != null) {
            existingEmployee.setStartDate(employee.getStartDate());
        }
        if (employee.getEndDate() != null) {
            existingEmployee.setEndDate(employee.getEndDate());
        }
        if (employee.getDriverLicense() != null) {
            existingEmployee.setDriverLicense(employee.getDriverLicense());
        }
        if (employee.getDriverLicenseExpiration() != null) {
            existingEmployee.setDriverLicenseExpiration(employee.getDriverLicenseExpiration());
        }
        if (employee.getHouseID() != null) {
            existingEmployee.setHouseID(employee.getHouseID());
        }
        if (existingEmployee.getAddresses() != null) {
            existingEmployee.setAddresses(employee.getAddresses());
        }
        if (existingEmployee.getContacts() != null) {
            existingEmployee.setContacts(employee.getContacts());
        }
        if (existingEmployee.getVisaStatuses() != null) {
            existingEmployee.setVisaStatuses(employee.getVisaStatuses());
        }

        Employee updatedEmployee = service.saveEmployee(existingEmployee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    // Add an address to an employee by employee ID
    @PutMapping("/{userId}/address")
    public ResponseEntity<Employee> addAddressToEmployee(@PathVariable("userId") Integer userId, @RequestBody Address address) {
        Employee existingEmployee = service.findEmployeeByUserID(userId);
        if (existingEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (existingEmployee.getAddresses() == null) {
            existingEmployee.setAddresses(new ArrayList<>());
        }
        existingEmployee.getAddresses().add(address);
        Employee updatedEmployee = service.saveEmployee(existingEmployee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    // Add a contact to an employee by employee ID
    @PutMapping("/{userId}/contact")
    public ResponseEntity<Employee> addContactToEmployee(@PathVariable("userId") Integer userId, @RequestBody Contact contact) {
        System.out.println(userId);
        System.out.println(contact);
        Employee existingEmployee = service.findEmployeeByUserID(userId);
        if (existingEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (existingEmployee.getContacts() == null) {
            existingEmployee.setContacts(new ArrayList<>());
        }
        existingEmployee.getContacts().add(contact);
        Employee updatedEmployee = service.saveEmployee(existingEmployee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    // edit contacts
    @PatchMapping("/{userId}/contacts")
    public ResponseEntity<Employee> updateContactsOfEmployee(@PathVariable("userId") Integer userId, @RequestBody List<Contact> contacts) {
        Employee existingEmployee = service.findEmployeeByUserID(userId);
        if (existingEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingEmployee.setContacts(contacts);
        Employee updatedEmployee = service.saveEmployee(existingEmployee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    // edit addresses
    @PatchMapping("/{userId}/addresses")
    public ResponseEntity<Employee> updateAddressesOfEmployee(@PathVariable("userId") Integer userId, @RequestBody List<Address> addresses) {
        Employee existingEmployee = service.findEmployeeByUserID(userId);
        if (existingEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingEmployee.setAddresses(addresses);
        Employee updatedEmployee = service.saveEmployee(existingEmployee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }



    @GetMapping("/all/status")
    public ResponseEntity<AllEmployeeVisaStatusResponse> findAllEmployeeStatus(){
        return new ResponseEntity<>(service.findAllEmployeeStatus(), HttpStatus.OK);
    }

    @GetMapping("/all/summary-view")
    public ResponseEntity<List<EmployeeSummaryViewResponse>> getEmployeesSummaryView(){
        return new ResponseEntity<>(service.getAllEmployeeSummaryView(), HttpStatus.OK);
    }

    @GetMapping("/all/visa-status-management")
    public ResponseEntity<List<VisaStatusManagementResponse>> getVisaStatusManagement(){
        return new ResponseEntity<>(service.getAllVisaStatusManagement(), HttpStatus.OK);
    }

    @PutMapping("/{id}/visa-status-management")
    public Employee updateVisaStatusManagement(@PathVariable int id,
                                          @RequestParam String type,
                                          @RequestParam String status){
        return service.updateVisaStatusManagement(id, type, status);
    }
}
