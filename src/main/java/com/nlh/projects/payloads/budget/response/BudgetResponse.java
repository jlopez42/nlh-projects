package com.nlh.projects.payloads.budget.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class BudgetResponse  extends MessageResponse{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Budgets> projectList;

    public BudgetResponse(String message, String code) {
        super(message, code);
    }

    public BudgetResponse(String message, String code, List<Budgets> projectList) {
        super(message, code);
        this.projectList = projectList;
    }

    public BudgetResponse() {
        super();
    }

    public List<Budgets> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Budgets> projectList) {
        this.projectList = projectList;
    }
}
