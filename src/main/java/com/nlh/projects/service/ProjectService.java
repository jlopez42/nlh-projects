package com.nlh.projects.service;

import com.nlh.projects.payloads.project.request.ProjectRequest;
import com.nlh.projects.payloads.project.response.ProjectResponse;

public interface ProjectService {

    public ProjectResponse newProject(ProjectRequest request);

    public ProjectResponse updateProject(ProjectRequest project, Long projectId) ;

    public ProjectResponse removeProject(Long projectId) ;

    public ProjectResponse getProject(Long projectId);

    public ProjectResponse list();

}
