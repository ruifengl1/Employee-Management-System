package com.team.project.entity;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class FinalFacilityReport {
    private String author;
    private Integer facilityReportId;
    private Integer employeeId;
    private String title;
    private String description;
    private Timestamp createDate;
    private Timestamp lastModifyDate;
    private String status;
    private List<FacilityReportDetail> facilityReportDetail;
    private Facility facility;
}
