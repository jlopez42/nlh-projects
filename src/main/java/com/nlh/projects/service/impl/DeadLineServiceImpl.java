package com.nlh.projects.service.impl;


import com.nlh.projects.payloads.tool.request.DeadLineRequest;
import com.nlh.projects.payloads.tool.response.DeadLineResponse;
import com.nlh.projects.payloads.tool.response.DeadLines;
import com.nlh.projects.repository.DeadLineRepository;
import com.nlh.projects.repository.entity.ProjectDeadline;
import com.nlh.projects.service.DeadLineService;
import com.nlh.projects.util.WrapperTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeadLineServiceImpl implements DeadLineService {

    @Autowired
    private DeadLineRepository repository;

    public DeadLineServiceImpl(DeadLineRepository repository) {
        this.repository = repository;
    }

    public DeadLineResponse newDeadLine(DeadLineRequest request) {
        if(!repository.existsByProjectId(request.getDeadLine().getProjectId())){
            ProjectDeadline projectDeadline = repository.save(WrapperTool.deadlineFrom(request.getDeadLine()));
            return new DeadLineResponse("DeadLine has associated to project successfully",
                    HttpStatus.CREATED.toString(),
                    List.of(new DeadLines(
                            projectDeadline.getId(),
                            projectDeadline.getProject().getName(),
                            Date.from(Instant.now()))));
        }
        return new DeadLineResponse("This DeadLine have not been associated",HttpStatus.CONFLICT.toString());
    }

    public DeadLineResponse list(){
        List<ProjectDeadline> projectDeadlines = repository.findAll();
        if(!projectDeadlines.isEmpty()) {
            return new DeadLineResponse("DeadLine associated",
                    HttpStatus.ACCEPTED.toString(),
                    WrapperTool.convertDeadLineTo(projectDeadlines));
        }
        return new DeadLineResponse("Does not exist DeadLine associated",HttpStatus.NOT_FOUND.toString());
    }

    public DeadLineResponse updateDeadLine(DeadLineRequest deadLineRequest, int deadLineId) {
        DeadLineResponse response =new DeadLineResponse();
        try {
            Optional<ProjectDeadline> deadlineCreated = repository.findById((long) deadLineId);
            if (deadlineCreated.isPresent()) {
                ProjectDeadline deadlineUpgrade = WrapperTool.deadlineFrom(deadLineRequest.getDeadLine());
                ProjectDeadline deadlineUpdate = repository.save(WrapperTool.deadlineUpdateFrom(deadlineCreated.get(), deadlineUpgrade));
                response.setMessage("The DeadLine has been updated successfully");
                response.setCode(HttpStatus.OK.toString());
                response.setDeadLines(List.of(new DeadLines(
                        deadlineUpdate.getId(),
                        deadlineUpdate.getProject().getName(),
                        new Date())));
            }
        } catch (Exception exception) {
            return new DeadLineResponse("Error::Updating DeadLine::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
        }
        return response;
    }

    public DeadLineResponse removeDeadLine(Long documentId) {
        DeadLineResponse response =new DeadLineResponse();
        try {
            Optional<ProjectDeadline> projectDeadline = repository.findById(documentId);
            if (projectDeadline.isPresent()) {
                repository.delete(projectDeadline.get());
                response.setMessage("The projectDeadline has been removed successfully");
                response.setCode(HttpStatus.OK.toString());
                response.setDeadLines(List.of(new DeadLines(
                        projectDeadline.get().getId(),
                        projectDeadline.get().getProject().getName(),
                        new Date())));
            } else {
                response.setMessage("Don't found DeadLine with id "+ documentId);
                response.setCode(HttpStatus.NOT_FOUND.toString());
                response.setDeadLines(null);
            }
        } catch (Exception exception) {
            return new DeadLineResponse("Error::Removing DeadLine::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
        }
        return response;
    }
}
