package com.team.project.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    private Long id;
    @Field("firstName")
    private String firstName;
    @Field("LastName")
    private String lastName;
    @Field("Email")
    private String email;
    @Field("CellPhone")
    private Integer cellPhone;
    @Field("AlternatePhone")
    private Integer alternatePhone;
    @Field("Relationship")
    private String relationship;
}
