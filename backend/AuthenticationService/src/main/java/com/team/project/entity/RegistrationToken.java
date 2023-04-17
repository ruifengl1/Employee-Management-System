package com.team.project.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
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
@Table(name="RegistrationToken")
public class RegistrationToken implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer registrationTokenId;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private LocalDateTime expirationDate;
    @Column(nullable = false)
    @Builder.Default()
    private Boolean consumed = false;
    /*@Column(nullable = false)
    private Integer createBy;*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "createBy", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonAlias("createBy")
    private User createBy;
}