package com.nlh.projects.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Entity(name = "projects")
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    @OneToOne
    @JoinColumn(name = "general_id")
    private General general;

    @OneToOne
    @JoinColumn(name = "detail_id")
    private Detail detail;

    @OneToOne
    @JoinColumn(name = "officer_id")
    private Officer officer;

    @OneToOne
    @JoinColumn(name = "extra_id")
    private Extra extra;
    private Date createdAt;
    private Date updatedAt;
}
