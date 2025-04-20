package com.nlh.projects.repository.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ProjectAssignmentId implements Serializable {
    private static final long serialVersionUID = -651816157505136260L;
}