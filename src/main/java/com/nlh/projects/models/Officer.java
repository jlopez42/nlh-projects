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
@Table(name = "project_officer")
public class Officer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String customer1;

    @NotBlank
    private String customer2;

    @NotBlank
    private String professional1;

    @NotBlank
    private String professional2;

    @NotBlank
    private String specialist1;
    @NotBlank
    private String specialist2;

    @NotBlank
    private String contact;
    @OneToOne(mappedBy = "officer")
    private Project project;
}
