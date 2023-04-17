package com.team.project.domain;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinalFacilityReportDetail {
    private String author;
    private Integer facilityReportDetailId;
    private Integer userId;
    private String comment;
    private Timestamp createDate;
}
