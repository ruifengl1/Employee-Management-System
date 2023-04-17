package com.team.project.entity.EmployeeService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    private List<VisaStatus> visaStatuses;
    private List<PersonalDocument> personalDocuments;


}

