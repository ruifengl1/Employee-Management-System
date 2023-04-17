package com.team.project.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value= {"houses"})
public class Landlord {

    private Integer landlordId;
    private String firstName;
    private String lastName;
    private String email;
    private String cellphone;



}
