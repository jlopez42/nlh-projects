package com.nlh.projects.payloads.budget.response;

import java.util.Date;

public class Budgets {
    private Long id;
    private Long projectId;
    private Date createdAt;

    public Budgets(Long id, Long projectId, Date createdAt) {
        this.id = id;
        this.projectId = projectId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
