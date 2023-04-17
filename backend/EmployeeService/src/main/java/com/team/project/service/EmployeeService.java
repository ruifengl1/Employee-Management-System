package com.team.project.service;

import com.team.project.domain.response.*;
import com.team.project.entity.Employee;
import com.team.project.entity.PersonalDocument;
import com.team.project.entity.VisaDocument;
import com.team.project.entity.VisaStatus;
import com.team.project.repository.EmployeeRepo;
import com.team.project.utils.LastNameSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAllEmployees();
    }
    public List<Employee> findEmployeeByHouseId(Integer houseId){
        List<Employee> employees = employeeRepo.findEmployeeByHouseId(houseId);
        if(employees.isEmpty()) return new ArrayList<>();
        return employeeRepo.findEmployeeByHouseId(houseId);
    }
    public Employee findEmployeeByUserID(Integer userId) {
        List<Employee> employees = employeeRepo.findEmployeeByUserID(userId);
        if(employees.isEmpty()) return null;
        return employees.get(0);
    }
    public List<VisaStatus> findEmployeeVisaStatus(Integer userId) {
        List<Employee> employees = employeeRepo.findEmployeeByUserID(userId);
        if(employees.isEmpty()) return null;
        return employees.get(0).getVisaStatuses();
    }

    public List<PersonalDocument> findEmployeePersonalDocument(Integer userId) {
        List<Employee> employees = employeeRepo.findEmployeeByUserID(userId);
        if(employees.isEmpty()) return null;
        return employees.get(0).getPersonalDocuments();
    }

    public List<VisaDocument> findEmployeeVisaStatusDocument(Integer userId) {
        List<VisaStatus> visaStatuses = findEmployeeVisaStatus(userId);
        List<PersonalDocument> personalDocuments = findEmployeePersonalDocument(userId).stream()
                .filter(pd-> pd.getDocumentType().equalsIgnoreCase("visa"))
                .collect(Collectors.toList());
        Map<String, VisaDocument> mapOnType = new HashMap<>();
        for (VisaStatus v: visaStatuses) {
            mapOnType.put(v.getVisaType(), VisaDocument.builder().visaStatus(v).build());
        }
        for(PersonalDocument d: personalDocuments){
            mapOnType.get(d.getTitle()).setPersonalDocument(d);
        }
        return mapOnType.values().stream()
                .sorted((a, b) -> a.getVisaStatus().getStartDate().compareTo(b.getVisaStatus().getStartDate()))
                .collect(Collectors.toList());
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
    public AllEmployeeVisaStatusResponse findAllEmployeeStatus() {
        List<Employee> employees = findAllEmployees();
        List<EmployeeStatusResponse> employeeStatusResponses = new ArrayList<>();
        List<WorkAuthorizationResponse> workAuthorizations = null;

        for (Employee employee : employees) {
            // employee's active authorization
            if (employee.getVisaStatuses() == null) continue;
            workAuthorizations = new ArrayList<>();
            for (VisaStatus visaStatus : employee.getVisaStatuses()) {
                if (!visaStatus.getActiveFlag().equals("APPROVED") || visaStatus.getEndDate() == null) continue;
                long daysLeft = ChronoUnit.DAYS.between(
                        LocalDate.now(),
                        visaStatus.getEndDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate());
                if (daysLeft < 0) continue;
                workAuthorizations.add(WorkAuthorizationResponse.builder()
                        .visaType(visaStatus.getVisaType())
                        .expirationDate(visaStatus.getEndDate())
                        .daysLeft(daysLeft)
                        .build());

            }
            // employee's name
            EmployeeStatusResponse employeeStatusResponse = null;
            if (!workAuthorizations.isEmpty()) {
                employeeStatusResponse = EmployeeStatusResponse.builder()
                        .name(employee.getFirstName() + " " + employee.getLastName())
                        .workAuthorizationResponses(workAuthorizations)
                        .build();
            }

            if(employeeStatusResponse != null) {
                employeeStatusResponses.add(employeeStatusResponse);
            }
        }

        return AllEmployeeVisaStatusResponse.builder().allStatus(employeeStatusResponses).build();
    }
    public void updateEmployeeVisaDocument(Integer userId, Employee employee) {
        employeeRepo.updateEmployeeVisaDocument(userId, employee);
    }

    public List<EmployeeSummaryViewResponse> getAllEmployeeSummaryView(){
        List<EmployeeSummaryViewResponse> employeeSummaryViewResponses = new ArrayList<>();
        List<Employee> employees = employeeRepo.getAllEmployeeForSummary();
        if(employees.isEmpty()) return null;
        for (Employee emp: employees) {
            String workAuthTitle = null;
            Optional<List<VisaStatus>> possibleVisaStatus= Optional.ofNullable(emp.getVisaStatuses());
            // find active visa type
            if(possibleVisaStatus.isPresent()) {
                for (int i = emp.getVisaStatuses().size() - 1; i >= 0; i--) {
                    VisaStatus visaStatus = emp.getVisaStatuses().get(i);
                    if (visaStatus.getActiveFlag().equals("APPROVED")) {
                        workAuthTitle = visaStatus.getVisaType();
                        break;
                    }
                }

            }
            // build response
            employeeSummaryViewResponses.add(
                EmployeeSummaryViewResponse.builder()
                        .id(emp.getUserID())
                        .firstName(emp.getFirstName())
                        .lastName(emp.getLastName())
                        .ssn(emp.getSsn())
                        .workAuthorizationTitle(workAuthTitle)
                        .phoneNumber(emp.getCellPhone())
                        .email(emp.getEmail())
                        .build()
            );
        }

        // sort by last name alphabetically
        return employeeSummaryViewResponses.stream().sorted(new LastNameSorter()).collect(Collectors.toList());

    }

    public List<VisaStatusManagementResponse> getAllVisaStatusManagement(){
        List<Employee> employees = employeeRepo.getAllVisaStatusManagement();
        if(employees.isEmpty()) return null;
        return employees.stream()
                .filter(emp -> emp.getVisaStatuses()!= null)
                .filter( emp -> !emp.getVisaStatuses().isEmpty())
                .map( emp ->
                    VisaStatusManagementResponse.builder()
                            .userId(emp.getUserID())
                            .firstName(emp.getFirstName())
                            .lastName(emp.getLastName())
                            .visaStatuses(emp.getVisaStatuses())
                            .build()
                    ).collect(Collectors.toList());

    }

    public Employee updateVisaStatusManagement(Integer userId, String visaType,String status){
        return employeeRepo.updateVisaActiveFlag(userId, visaType, status);
    }
}
