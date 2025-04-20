package com.nlh.projects.service;

import com.nlh.projects.payloads.budget.request.BudgetRequest;
import com.nlh.projects.payloads.budget.response.BudgetResponse;

public interface BudgetService {

    public BudgetResponse newBudget(BudgetRequest request);

    public BudgetResponse listAllBudget() ;

    public BudgetResponse listByProjectId(Long projectId) ;

    public BudgetResponse getBudgetById(Long budgetId) ;

    public BudgetResponse updatedBudget(BudgetRequest budget, Long budgetId) ;

    public BudgetResponse removeBudget(Long budgetId) ;
}
