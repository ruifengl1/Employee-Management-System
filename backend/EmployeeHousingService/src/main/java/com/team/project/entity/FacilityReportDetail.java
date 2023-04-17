package com.team.project.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityReportDetail {

    private Integer facilityReportDetailId;
    private Integer userId;
    private String comment;
    private Timestamp createDate;

}
