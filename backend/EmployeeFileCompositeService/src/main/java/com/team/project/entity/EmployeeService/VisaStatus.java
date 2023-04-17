package com.team.project.entity.EmployeeService;

import lombok.*;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VisaStatus {
    private String id;
    private String visaType;
    private String activeFlag;
    private Timestamp startDate;
    private Timestamp endDate;
    private Timestamp lastModificationDate;
    private String nextStep;
}

