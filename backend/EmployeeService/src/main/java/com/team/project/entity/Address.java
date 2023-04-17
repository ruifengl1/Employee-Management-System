package com.team.project.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "Address")
public class Address {
    private String id;
    @Field("AddressLine1")
    private String addressLine1;
    @Field("AddressLine2")
    private String addressLine2;
    @Field("City")
    private String city;
    @Field("State")
    private String state;
    @Field("ZipCode")
    private Integer zipCode;
}
