package com.nlh.projects.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "project_extra")
public class Extra {

    @Id
    @GeneratedValue
    private Long id;
    private String additional;

    @OneToOne(mappedBy = "extra")
    private Project project;
}
