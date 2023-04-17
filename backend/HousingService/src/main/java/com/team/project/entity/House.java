package com.team.project.entity;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="house")
@JsonIgnoreProperties(value= {"facilities"})

public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="houseId")
    private Integer houseId;
    @Column(name="address")
    private String address;
    @Column(name="maxOccupant")
    private Integer maxOccupant;
    @OneToMany(mappedBy="house")
    private Set<Facility> facilities;
    @ManyToOne
    @JoinColumn(name="landlordId", nullable=false)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonAlias("landlordId")
    private Landlord landlord;

}
