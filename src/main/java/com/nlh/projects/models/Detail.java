package com.nlh.projects.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project_details")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Integer quantity;
    private Integer flats;
    private String rawMaterial;
    private String surface;
    private String area1;
    private String area2;
    private String area3;
    private String area4;
    private String area5;
    private String area6;
    private String area7;

    @OneToOne(mappedBy = "detail")
    private Project project;
}
