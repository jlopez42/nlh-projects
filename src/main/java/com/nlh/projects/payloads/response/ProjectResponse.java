package com.nlh.projects.payloads.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ProjectResponse extends MessageResponse{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Projects> projectList;

    public ProjectResponse(String message, String code) {
        super(message, code);
    }

    public ProjectResponse(String message, String code, List<Projects> projectList) {
        super(message, code);
        this.projectList = projectList;
    }

    public ProjectResponse() {
        super();
    }

    public List<Projects> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Projects> projectList) {
        this.projectList = projectList;
    }
}
