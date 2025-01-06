package com.nlh.projects.service;

import com.nlh.projects.payloads.request.ProjectRequest;
import com.nlh.projects.payloads.response.ProjectResponse;
import com.nlh.projects.payloads.response.Projects;
import com.nlh.projects.repository.ProjectDetailAreaRepository;
import com.nlh.projects.repository.ProjectOfficerStaffRepository;
import com.nlh.projects.repository.ProjectRepository;
import com.nlh.projects.repository.entity.Project;
import com.nlh.projects.repository.entity.ProjectTypeStaff;
import com.nlh.projects.util.WrapperProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private final ProjectRepository repository;

    @Autowired
    private final ProjectDetailAreaRepository detailAreaRepository;

    @Autowired
    private final ProjectOfficerStaffRepository officerStaffRepository;

    public ProjectService(ProjectRepository repository, ProjectDetailAreaRepository detailAreaRepository, ProjectOfficerStaffRepository officerStaffRepository) {
        this.repository = repository;
        this.detailAreaRepository = detailAreaRepository;
        this.officerStaffRepository = officerStaffRepository;
    }

    /**
     * Method used to create new projects
     * @param request
     * @return
     */
    public ProjectResponse newProject(ProjectRequest request){
        ProjectResponse response =new ProjectResponse();
        try {
          if (!repository.existsByName(request.getProject().getName())) {
              Project project = repository.save(WrapperProject.projectFrom(request.getProject()));
              if (saveDetailAreas(project) && saveDetailOfficer(project) ){
                  response.setMessage("The project has been created successfully");
                  response.setCode(HttpStatus.CREATED.toString());
                  response.setProjectList(List.of(new Projects(
                          project.getId(),
                          project.getName(),
                          Date.from(project.getCreatedAt()))));
              } else {
                  response.setMessage("The project has been created with saving details areas and officers");
                  response.setCode(HttpStatus.CONTINUE.toString());
                  response.setProjectList(List.of(new Projects(
                          project.getId(),
                          project.getName(),
                          Date.from(project.getCreatedAt()))));
              }
          } else {
              response.setMessage("The project has just been created previously");
              response.setCode(HttpStatus.ACCEPTED.toString());
          }
      } catch (Exception exception) {
          return new ProjectResponse("Error::Creating project::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
      }
        return response;
    }

    private boolean saveDetailAreas(Project project) {
        try {
            if(Objects.nonNull(project)){
                //insert Detail Areas
                project.getProjectDetails().getProjectDetailAreas().forEach(area -> {
                    area.setProjectDetails(project.getProjectDetails());
                    detailAreaRepository.save(area);
                });
            }
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    private boolean saveDetailOfficer(Project project) {
        try {
            if(Objects.nonNull(project)){
                //insert Officers
                project.getProjectOfficers().getProjectOfficerStaffs().forEach(staff -> {
                    staff.setName(staff.getName());
                    staff.setProjectOfficer(project.getProjectOfficers());
                    ProjectTypeStaff projectTypeStaff = new ProjectTypeStaff();

                    projectTypeStaff.setId(staff.getId());
                    staff.setTypeStaff(projectTypeStaff);
                    officerStaffRepository.save(staff);
                });
            }
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    public ProjectResponse updateProject(ProjectRequest project, Long projectId) {
        ProjectResponse response =new ProjectResponse();
        try {
            Optional<Project> projectCreated = repository.findById(projectId);
            if (projectCreated.isPresent()) {
                Project projectUpgrade = WrapperProject.projectFrom(project.getProject());
                Project projectUpdate = repository.save(WrapperProject.projectUpdateFrom(projectCreated.get(), projectUpgrade));
                response.setMessage("The project has been updated successfully");
                response.setCode(HttpStatus.OK.toString());
                response.setProjectList(List.of(new Projects(
                        projectUpdate.getId(),
                        projectUpdate.getName(),
                        Date.from(projectUpdate.getUpdatedAt()))));
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
                        Date.from(project.get().getUpdatedAt()))));
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
                        Date.from(project.get().getCreatedAt()))));
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
