package com.team.project.domain.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class updateFacilityReportDetail {
    private Integer userId;
    private String comment;
}
