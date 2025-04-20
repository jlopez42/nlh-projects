package com.nlh.projects.service.impl;


import com.nlh.projects.payloads.tool.request.ExecutionDateRequest;
import com.nlh.projects.payloads.tool.response.ExecutionDateResponse;
import com.nlh.projects.payloads.tool.response.Executions;
import com.nlh.projects.repository.ExecutionDateRepository;
import com.nlh.projects.repository.entity.ProjectSchedule;
import com.nlh.projects.service.ExecutionDateService;
import com.nlh.projects.util.WrapperTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExecutionDateServiceImpl implements ExecutionDateService {

    @Autowired
    private ExecutionDateRepository repository;

    public ExecutionDateServiceImpl(ExecutionDateRepository repository) {
        this.repository = repository;
    }

    public ExecutionDateResponse newExecution(ExecutionDateRequest request) {
        if(!repository.existsByProjectId(request.getExecution().getProjectId())){
            ProjectSchedule projectSchedule = repository.save(WrapperTool.executionFrom(request.getExecution()));
            return new ExecutionDateResponse("DeadLine has associated to project successfully",
                    HttpStatus.CREATED.toString(),
                    List.of(new Executions(
                            projectSchedule.getId(),
                            projectSchedule.getProject().getName(),
                            Date.from(Instant.now()))));
        }
        return new ExecutionDateResponse("This DeadLine have not been associated",HttpStatus.CONFLICT.toString());
    }

    public ExecutionDateResponse list(){
        List<ProjectSchedule> projectSchedules = repository.findAll();
        if(!projectSchedules.isEmpty()) {
            return new ExecutionDateResponse("DeadLine associated",
                    HttpStatus.ACCEPTED.toString(),
                    WrapperTool.convertExecutionTo(projectSchedules));
        }
        return new ExecutionDateResponse("Does not exist DeadLine associated",HttpStatus.NOT_FOUND.toString());
    }

    public ExecutionDateResponse updateExecution(ExecutionDateRequest executionDateRequest, int executionId) {
        ExecutionDateResponse response =new ExecutionDateResponse();
        try {
            Optional<ProjectSchedule> projectScheduleCreated = repository.findById((long) executionId);
            if (projectScheduleCreated.isPresent()) {
                ProjectSchedule projectScheduleUpgrade = WrapperTool.executionFrom(executionDateRequest.getExecution());
                ProjectSchedule projectScheduleUpdate = repository.save(WrapperTool.executionUpdateFrom(projectScheduleCreated.get(), projectScheduleUpgrade));
                response.setMessage("The DeadLine has been updated successfully");
                response.setCode(HttpStatus.OK.toString());
                response.setExecutions(List.of(new Executions(
                        projectScheduleUpdate.getId(),
                        projectScheduleUpdate.getProject().getName(),
                        new Date())));
            }
        } catch (Exception exception) {
            return new ExecutionDateResponse("Error::Updating DeadLine::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
        }
        return response;
    }

    public ExecutionDateResponse removeExecution(Long executionId) {
        ExecutionDateResponse response =new ExecutionDateResponse();
        try {
            Optional<ProjectSchedule> projectSchedule = repository.findById(executionId);
            if (projectSchedule.isPresent()) {
                repository.delete(projectSchedule.get());
                response.setMessage("The projectDeadline has been removed successfully");
                response.setCode(HttpStatus.OK.toString());
                response.setExecutions(List.of(new Executions(
                        projectSchedule.get().getId(),
                        projectSchedule.get().getProject().getName(),
                        new Date())));
            } else {
                response.setMessage("Don't found DeadLine with id "+ executionId);
                response.setCode(HttpStatus.NOT_FOUND.toString());
                response.setExecutions(null);
            }
        } catch (Exception exception) {
            return new ExecutionDateResponse("Error::Removing DeadLine::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
        }
        return response;
    }
}
