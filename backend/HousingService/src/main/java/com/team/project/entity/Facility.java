package com.team.project.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="facility")
//@JsonIgnoreProperties(value= {"facilityReports"})
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="facilityId")
    private Integer facilityId;
    @Column(name="facilityType")
    private String facilityType;
    @Column(name="description")
    private String description;
    @Column(name="quantity")
    private Integer quantity;
    @OneToMany(mappedBy="facility", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FacilityReport> facilityReports = new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="houseId", nullable=false)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonAlias("houseId")
    private House house;
}

