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
@Document(collection = "PersonalDocument")
public class PersonalDocument {
    private String id;
    @Field("Path")
    private String path;
    @Field("Comment")
    private String comment;
    @Field("Title")
    private String title;
    @Field("CreateDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    @Field("DocumentType")
    private String documentType;
}
