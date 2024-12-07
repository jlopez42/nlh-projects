package com.nlh.projects.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "project_details")
public class Detail {

    @Id
    @GeneratedValue
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
