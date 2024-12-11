package com.nlh.projects.service;

import com.nlh.projects.models.Project;
import com.nlh.projects.payloads.request.ProjectRequest;
import com.nlh.projects.payloads.response.ProjectResponse;
import com.nlh.projects.payloads.response.Projects;
import com.nlh.projects.repository.ProjectRepository;
import com.nlh.projects.util.WrapperProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }
    public ProjectResponse newProject(ProjectRequest request){

        if(!repository.existsByName(request.getProject().getName())){
            Project project = repository.save(request.getProject());
            return new ProjectResponse("The project has created successfully",
                     "201", List.of(new Projects(
                        project.getId(),
                        project.getName(),
                        project.getCreatedAt())));
        }
        return new ProjectResponse("The project have been creating","409");
    }

    public ProjectResponse list(){

            List<Project> projects = repository.findAll();
            if(!projects.isEmpty()){
            return new ProjectResponse("These projects are associated",
                    "201",
                    WrapperProject.convertTo(projects));
            }
        return new ProjectResponse("Does not exist projects created","409");
    }
}
