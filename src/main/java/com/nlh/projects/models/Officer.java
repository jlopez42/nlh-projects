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
@Table(name = "project_officer")
public class Officer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customer1;
    private String customer2;
    private String professional1;
    private String professional2;
    private String specialist1;
    private String specialist2;
    private String contact;
    @OneToOne(mappedBy = "officer")
    private Project project;
}
