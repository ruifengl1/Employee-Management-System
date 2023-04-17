package com.team.project.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class House {

    private Integer houseId;
    private String address;
    private Integer maxOccupant;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private Integer facilityId;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private Landlord landlord;

}
