package com.team.project.entity;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="facilityReport")
//@JsonIgnoreProperties(value= {"facilityReportDetail"})

public class FacilityReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="facilityReportId")
    private Integer facilityReportId;
    @Column(name="employeeId")
    private Integer employeeId;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="createDate")
    private Timestamp createDate;
    @Column(name="lastModifyDate")
    private Timestamp lastModifyDate;
    @Column(name="status")
    private String status;
    @OneToMany(mappedBy="facilityReport", cascade = CascadeType.ALL)
//    @JsonIgnore
//    @JsonIgnore
    private List<FacilityReportDetail> facilityReportDetail;
    @ManyToOne
    @JoinColumn(name = "facilityId")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonAlias("facilityId")
//    @JsonIgnore
    private Facility facility;
}
