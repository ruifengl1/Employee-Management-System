package com.team.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "VisaStatus")
public class VisaStatus {
    private String id;
    @Field("VisaType")
    private String visaType;
    @Field("ActiveFlag")
    private String activeFlag;
    @Field("StartDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @Field("EndDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    @Field("LastModificationDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date lastModificationDate;
}
