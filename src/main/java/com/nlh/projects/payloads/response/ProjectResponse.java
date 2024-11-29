package com.nlh.projects.payloads.response;



import java.util.Date;

public class ProjectResponse extends MessageResponse{
    private Long id;
    private String name;
    private Date createdAt;

    public ProjectResponse(String message, String code) {
        super(message, code);
    }

    public ProjectResponse(String message, String code, Long id, String name, Date createdAt) {
        super(message, code);
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
