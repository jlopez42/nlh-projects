package com.nlh.projects.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String type;
    private Integer quantity;
    private Integer flats;
    @NotBlank
    private String rawMaterial;
    @NotBlank
    private String surface;
    @NotBlank
    private String area1;
    @NotBlank
    private String area2;
    private String area3;
    private String area4;
    private String area5;
    private String area6;
    private String area7;

    @OneToOne(mappedBy = "detail")
    private Project project;
}
