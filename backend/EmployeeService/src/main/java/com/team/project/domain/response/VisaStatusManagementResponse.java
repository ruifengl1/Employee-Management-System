package com.team.project.domain.response;

import com.team.project.entity.VisaStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VisaStatusManagementResponse {
    private Integer userId;
    private String firstName;
    private String lastName;
    private List<VisaStatus> visaStatuses;
}
