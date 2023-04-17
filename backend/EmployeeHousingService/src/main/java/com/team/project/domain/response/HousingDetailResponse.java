package com.team.project.domain.response;

import com.team.project.entity.Employee;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HousingDetailResponse {
    private String message;
    private List<Employee> roommates;
    private String address;
    private Integer houseId;
}
