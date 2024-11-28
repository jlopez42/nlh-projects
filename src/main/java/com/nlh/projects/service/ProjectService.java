package com.nlh.projects.service;

import com.nlh.projects.models.Project;
import com.nlh.projects.payloads.request.ProjectRequest;
import com.nlh.projects.payloads.response.MessageResponse;
import com.nlh.projects.payloads.response.ProjectResponse;
import com.nlh.projects.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }
    public ProjectResponse newProject(ProjectRequest request){

        if(repository.existsByName(request.getProject().getName())){
            Project project = repository.save(request.getProject());
            return ProjectResponse.builder()
                    .id(project.getId())
                    .name(project.getName())
                    .createdAt(project.getCreatedAt())
                    .build();
        }
        return (ProjectResponse) MessageResponse.builder().message("The project have been creating")
                .code("409").build();
    }
}
