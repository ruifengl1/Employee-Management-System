package com.team.project.domain.request;

import com.team.project.entity.Facility;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class addNewHouseRequest {
    private String address;
    private Integer maxOccupant;
    private String firstName;
    private String lastName;
    private String cellphone;
    private String email;
    private List<Facility> facilities;
}
