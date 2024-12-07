package com.nlh.projects.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "project_general")
public class General {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private String location;

    @OneToOne(mappedBy = "general")
    private Project project;
}
