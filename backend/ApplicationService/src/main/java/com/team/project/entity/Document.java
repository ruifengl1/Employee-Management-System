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
@Table(name="DigitalDocument")
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "digitalDocumentId")
    private Integer id;
    @Column(name = "type")
    private String type;
    @Column(name = "isRequired")
    private Boolean isRequired;
    @Column(name = "path")
    private String path;
    @Column(name = "description")
    private String description;
    @Column(name = "title")
    private String title;
}
