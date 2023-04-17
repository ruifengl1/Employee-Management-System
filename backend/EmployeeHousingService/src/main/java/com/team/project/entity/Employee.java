package com.team.project.entity;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private String ID;
    private Integer UserID;
    private String FirstName;
    private String LastName;
    private String MiddleName;
    private String PreferredName;
    private String Email;
    private String CellPhone;
    private String Gender;
    private Integer HouseID;

}
