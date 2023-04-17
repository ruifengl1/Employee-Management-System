package com.team.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Role")
public class Role implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer roleId;
    @Column(nullable = false)
    private String roleName;
    @Column(nullable = false)
    private String roleDescription;
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime lastModificationDate = LocalDateTime.now();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    @JsonIgnore // to avoid infinite loop
    @ToString.Exclude
    private Set<UserRole> userRoles = new HashSet<>(0);

}