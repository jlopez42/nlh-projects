package com.nlh.projects.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Entity
public class Project {

    @Id
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    //TODO: create database entities
    //private General general;
    //private Detail detail;
    //private Officer officer;
    //private Extra extra;
    private Date createdAt;
    private Date updatedAt;
}
