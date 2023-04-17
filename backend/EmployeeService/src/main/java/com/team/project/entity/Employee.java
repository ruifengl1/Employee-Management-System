package com.team.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "Employee")
public class Employee {
    private String id;
    @Field("UserID")
    private Integer userID;
    @Field("FirstName")
    private String firstName;
    @Field("LastName")
    private String lastName;
    @Field("MiddleName")
    private String middleName;
    @Field("preferredName")
    private String preferredName;
    @Field("Email")
    private String email;
    @Field("CellPhone")
    private String cellPhone;
    @Field("AlternatePhone")
    private String alternatePhone;
    @Field("Gender")
    private String gender;
    @Field("SSN")
    private String ssn;
    @Field("DOB")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dob;
    @Field("StartDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @Field("EndDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    @Field("DriverLicense")
    private String driverLicense;
    @Field("DriverLicenseExpiration")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date driverLicenseExpiration;
    @Field("HouseID")
    private Integer houseID;
    @Field("Contacts")
    private List<Contact> contacts;
    @Field("Addresses")
    private List<Address> addresses;
    @Field("VisaStatus")
    private List<VisaStatus> visaStatuses;
    @Field("PersonalDocument")
    private List<PersonalDocument> personalDocuments;


}
