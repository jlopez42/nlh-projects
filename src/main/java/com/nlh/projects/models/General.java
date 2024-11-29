package com.nlh.projects.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class General {

    @Id
    private Long id;
    private String description;
    private String location;
}
