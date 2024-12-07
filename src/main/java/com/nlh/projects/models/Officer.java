package com.nlh.projects.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "project_officer")
public class Officer {

    @Id
    @GeneratedValue
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
