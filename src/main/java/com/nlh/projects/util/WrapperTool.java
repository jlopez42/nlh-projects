package com.nlh.projects.util;


import com.nlh.projects.models.tool.DeadLine;
import com.nlh.projects.models.tool.Document;
import com.nlh.projects.models.tool.Execution;
import com.nlh.projects.payloads.tool.response.DeadLines;
import com.nlh.projects.payloads.tool.response.Documents;
import com.nlh.projects.payloads.tool.response.Executions;
import com.nlh.projects.repository.entity.Project;
import com.nlh.projects.repository.entity.ProjectDeadline;
import com.nlh.projects.repository.entity.ProjectSchedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WrapperTool {

    public static List<Documents> convertTo (List<com.nlh.projects.repository.entity.Document> projectList){
        List<Documents> projects = new ArrayList<>();
        projectList.forEach(project -> projects.add(new Documents(project.getId(), project.getTitle(), Date.from(project.getCreatedAt()))));
        return projects;
    }

    public static com.nlh.projects.repository.entity.Document documentFrom(Document document) {
        com.nlh.projects.repository.entity.Document entity = new com.nlh.projects.repository.entity.Document();
        entity.setTitle(document.getTitle());
        Project project = new Project();
        project.setId(document.getProjectId());
        entity.setProject(project);
        entity.setContent(document.getContent().getBytes());
        return entity;
    }

    public static com.nlh.projects.repository.entity.Document documentUpdateFrom(com.nlh.projects.repository.entity.Document document, com.nlh.projects.repository.entity.Document documentUpgrade) {
        document.setProject(documentUpgrade.getProject());
        document.setTitle(documentUpgrade.getTitle());
        document.setContent(documentUpgrade.getContent());
        return document;
    }

    public static ProjectDeadline deadlineFrom(DeadLine deadLine) {
        ProjectDeadline projectDeadline = new ProjectDeadline();
        projectDeadline.setProposal(LocalDate.from(deadLine.getProposal().toInstant()));
        projectDeadline.setAsk(LocalDate.from(deadLine.getAsk().toInstant()));
        projectDeadline.setReply(LocalDate.from(deadLine.getReply().toInstant()));
        Project project = new Project();
        project.setId(deadLine.getProjectId());
        projectDeadline.setProject(project);
        return projectDeadline;
    }

    public static List<DeadLines> convertDeadLineTo(List<ProjectDeadline> projectDeadlines) {
        List<DeadLines> deadLinesList = new ArrayList<>();
        projectDeadlines.forEach(projectDeadline -> deadLinesList.add(new DeadLines(projectDeadline.getId(), projectDeadline.getProject().getName(), new Date())));
        return deadLinesList;
    }

    public static ProjectDeadline deadlineUpdateFrom(ProjectDeadline projectDeadline, ProjectDeadline deadlineUpgrade) {
        projectDeadline.setAsk(deadlineUpgrade.getAsk());
        projectDeadline.setProject(deadlineUpgrade.getProject());
        projectDeadline.setReply(deadlineUpgrade.getReply());
        projectDeadline.setProposal(deadlineUpgrade.getProposal());
        return projectDeadline;
    }

    public static ProjectSchedule executionFrom(Execution execution) {
        ProjectSchedule projectSchedule = new ProjectSchedule();
        projectSchedule.setEndDate(LocalDate.from(execution.getEndProject().toInstant()));
        projectSchedule.setStartDate(LocalDate.from(execution.getStartProject().toInstant()));
        Project project = new Project();
        project.setId(execution.getProjectId());
        projectSchedule.setProject(project);
        return projectSchedule;
    }

    public static ProjectSchedule executionUpdateFrom(ProjectSchedule projectSchedule, ProjectSchedule projectScheduleUpgrade) {
        projectSchedule.setEndDate(projectScheduleUpgrade.getEndDate());
        projectSchedule.setStartDate(projectScheduleUpgrade.getStartDate());
        return projectSchedule;
    }

    public static List<Executions> convertExecutionTo(List<ProjectSchedule> projectSchedules) {
        List<Executions> executions = new ArrayList<>();
        projectSchedules.forEach(projectSchedule -> executions.add(new Executions(projectSchedule.getId(), projectSchedule.getProject().getName(), new Date())));
        return executions;
    }
}
