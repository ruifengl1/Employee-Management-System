package com.team.project.entity.EmployeeService;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PersonalDocument {
    private String id;
    private String path;
    private String comment;
    private String title;
    private Date createDate;
    private String documentType;
}

