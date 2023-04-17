package com.team.project.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="UserRole")
public class UserRole implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonIgnore
    private Integer userRoleId;
    /*@Column(nullable = false)
    private Integer userId;*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonAlias("userId")
    @ToString.Exclude
    @JsonIgnore
    private User user;
    /*@Column(nullable = false)
    private Integer roleId;*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonAlias("roleId")
    //@ToString.Exclude
    private Role role;
    @Column(nullable = false)
    @Builder.Default
    private boolean activeFlag = true;
    @Column(nullable = false)
    @JsonIgnore
    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();
    @Column(nullable = false)
    @JsonIgnore
    @Builder.Default
    private LocalDateTime lastModificationDate = LocalDateTime.now();


}