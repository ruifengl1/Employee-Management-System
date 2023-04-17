package com.team.project.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Application {
    private Integer id;
    private Integer employeeId;
    private Timestamp createDate;
    private Timestamp lastModificationDate;
    private String status;
    private String comment;
}
