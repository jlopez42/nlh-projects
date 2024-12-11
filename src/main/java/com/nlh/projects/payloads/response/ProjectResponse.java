package com.nlh.projects.payloads.response;

import java.util.List;

public class ProjectResponse extends MessageResponse{
    private List<Projects> projectList;

    public ProjectResponse(String message, String code) {
        super(message, code);
    }

    public ProjectResponse(String message, String code, List<Projects> projectList) {
        super(message, code);
        this.projectList = projectList;
    }

    public List<Projects> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Projects> projectList) {
        this.projectList = projectList;
    }
}
