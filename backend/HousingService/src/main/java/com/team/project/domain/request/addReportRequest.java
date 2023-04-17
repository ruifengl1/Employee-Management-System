package com.team.project.domain.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class addReportRequest {
    private Integer userId;
    private Integer facilityId;
    private String title;
    private String description;
}
