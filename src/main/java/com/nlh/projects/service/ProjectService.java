package com.nlh.projects.service;

import com.nlh.projects.models.Project;
import com.nlh.projects.payloads.request.ProjectRequest;
import com.nlh.projects.payloads.response.ProjectResponse;
import com.nlh.projects.payloads.response.Projects;
import com.nlh.projects.repository.ProjectRepository;
import com.nlh.projects.util.WrapperProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }
    public ProjectResponse newProject(ProjectRequest request){
        ProjectResponse response =new ProjectResponse();
        try {
          if (!repository.existsByName(request.getProject().getName())) {
              Project project = repository.save(request.getProject());
              response.setMessage("The project has been created successfully");
              response.setCode(HttpStatus.CREATED.toString());
              response.setProjectList(List.of(new Projects(
                      project.getId(),
                      project.getName(),
                      project.getCreatedAt())));
          }
      } catch (Exception exception) {
          return new ProjectResponse("Error::Creating project::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
      }
        return response;
    }

    public ProjectResponse updateProject(ProjectRequest project, Long projectId) {
        ProjectResponse response =new ProjectResponse();
        try {
            Optional<Project> projectCreated = repository.findById(projectId);
            if (projectCreated.isPresent()) {
                projectCreated.get().setName(project.getProject().getName());
                Project projectUpdate = repository.save(projectCreated.get());
                response.setMessage("The project has been updated successfully");
                response.setCode(HttpStatus.OK.toString());
                response.setProjectList(List.of(new Projects(
                        projectUpdate.getId(),
                        projectUpdate.getName(),
                        projectUpdate.getUpdatedAt())));
            }
        } catch (Exception exception) {
            return new ProjectResponse("Error::Updating project::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
        }
        return response;
    }

    public ProjectResponse removeProject(Long projectId) {
        ProjectResponse response =new ProjectResponse();
        try {
            Optional<Project> project = repository.findById(projectId);
            if (project.isPresent()) {
                repository.delete(project.get());
                response.setMessage("The project has been removed successfully");
                response.setCode(HttpStatus.OK.toString());
                response.setProjectList(List.of(new Projects(
                        project.get().getId(),
                        project.get().getName(),
                        project.get().getUpdatedAt())));
            } else {
                response.setMessage("Don't found project with id "+ projectId);
                response.setCode(HttpStatus.OK.toString());
                response.setProjectList(null);
            }
        } catch (Exception exception) {
            return new ProjectResponse("Error::Removing project::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
        }
        return response;
    }

    public ProjectResponse getProject(Long projectId){
        ProjectResponse response =new ProjectResponse();
        try {
            Optional<Project> project = repository.findById(projectId);
            if (project.isPresent()) {
                response.setMessage("Project found successfully by id " + projectId);
                response.setCode(HttpStatus.OK.toString());
                response.setProjectList(List.of(new Projects(
                        project.get().getId(),
                        project.get().getName(),
                        project.get().getCreatedAt())));
            } else {
                response.setMessage("Project not found successfully by id " + projectId);
                response.setCode(HttpStatus.OK.toString());
            }
        } catch (Exception exception) {
            return new ProjectResponse("Error::Getting project by Id::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
        }
        return response;
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
