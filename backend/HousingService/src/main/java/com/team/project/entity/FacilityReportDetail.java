package com.team.project.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="facilityReportDetail")
public class FacilityReportDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="facilityReportDetailId")
    private Integer facilityReportDetailId;
    @Column(name="userId")
    private Integer userId;
    @Column(name="comment")
    private String comment;
    @Column(name="createDate")
    private Timestamp createDate;
    @ManyToOne()
    @JoinColumn(name = "facilityReportId")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonAlias("facilityReportId")
    @JsonIgnore
    private FacilityReport facilityReport;
}
