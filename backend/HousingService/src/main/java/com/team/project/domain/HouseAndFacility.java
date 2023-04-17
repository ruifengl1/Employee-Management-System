package com.team.project.domain;

import com.team.project.entity.Facility;
import com.team.project.entity.House;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class HouseAndFacility {
    private House house;
    private List<Facility> facilityList;
}
