package com.nlh.projects.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "project_officer")
public class ProjectOfficer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Size(max = 255)
    @NotNull
    @Column(name = "contact", nullable = false)
    private String contact;

    @OneToMany(mappedBy = "projectOfficer", cascade = CascadeType.ALL)
    private Set<ProjectOfficerStaff> projectOfficerStaffs = new LinkedHashSet<>();

}