package com.team.project.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ApplicationWorkFlow")
public class Application implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicationWorkFlowId")
    private Integer id;
    @Column(name = "employeeId")
    private Integer employeeId;
    @Column(name = "createDate")
    private Timestamp createDate;
    @Column(name = "lastModificationDate")
    private Timestamp lastModificationDate;
    @Column(name = "status")
    private String status;
    @Column(name = "comment")
    private String comment;
}
