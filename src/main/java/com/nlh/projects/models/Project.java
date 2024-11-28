package com.nlh.projects.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Project {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private General general;
    private Detail detail;
    private Officer officer;
    private Extra extra;
    private Date createdAt;
    private Date updatedAt;
}
