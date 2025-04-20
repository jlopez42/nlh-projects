package com.nlh.projects.service.impl;

import com.nlh.projects.payloads.budget.request.BudgetRequest;
import com.nlh.projects.payloads.budget.response.BudgetResponse;
import com.nlh.projects.payloads.budget.response.Budgets;
import com.nlh.projects.repository.BudgetProjectRepository;
import com.nlh.projects.repository.BudgetRepository;
import com.nlh.projects.repository.entity.Budget;
import com.nlh.projects.service.BudgetService;
import com.nlh.projects.util.WrapperBudget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {
    @Autowired
    private BudgetRepository repository;

    @Autowired
    private BudgetProjectRepository budgetProjectRepository;

    public BudgetServiceImpl(BudgetRepository repository, BudgetProjectRepository budgetProjectRepository) {
        this.repository = repository;
        this.budgetProjectRepository = budgetProjectRepository;
    }
    public BudgetResponse newBudget(BudgetRequest request){
        BudgetResponse response = new BudgetResponse();
        if(budgetProjectRepository.existsByProjectId(request.getBudget().getProjectId())){
            Budget budget = repository.save(WrapperBudget.budgetFrom(request.getBudget()));
            response.setMessage("The project has been created successfully");
            response.setCode(HttpStatus.CREATED.toString());
            response.setProjectList(List.of(new Budgets(
                    budget.getId(),
                    budget.getProjectId(),
                    Date.from(budget.getCreatedAt().toInstant()))));
        }
        return new BudgetResponse("This budget have not been associated",HttpStatus.CONFLICT.toString());
    }

    public BudgetResponse listAllBudget() {
        List<Budget> budgets = repository.findAll();
        if(!budgets.isEmpty()){
            return new BudgetResponse("These projects are associated",
                    HttpStatus.ACCEPTED.toString(),
                    WrapperBudget.convertTo(budgets));
        }
        return new BudgetResponse("Does not exist projects created",HttpStatus.CONFLICT.toString());
    }

    public BudgetResponse listByProjectId(Long projectId) {
        Optional<Budget> budget = repository.findByProjectId(projectId);
        return budget.map(value -> new BudgetResponse("These projects are associated",
                HttpStatus.ACCEPTED.toString(),
                List.of(new Budgets(
                        value.getId(),
                        value.getDocumentId(),
                        Date.from(value.getCreatedAt().toInstant()))))).orElseGet(() -> new BudgetResponse("Does not exist projects created", HttpStatus.CONFLICT.toString()));

    }

    public BudgetResponse getBudgetById(Long budgetId) {
        Optional<Budget> budget = repository.findById(budgetId);
        return budget.map(value -> new BudgetResponse("These projects are associated",
                HttpStatus.ACCEPTED.toString(),
                List.of(new Budgets(
                        value.getId(),
                        value.getDocumentId(),
                        Date.from(value.getCreatedAt().toInstant()))))).orElseGet(() -> new BudgetResponse("Does not exist projects created", HttpStatus.CONFLICT.toString()));
    }

    public BudgetResponse updatedBudget(BudgetRequest budget, Long budgetId) {
        BudgetResponse response =new BudgetResponse();
        try {
            Optional<Budget> budgetCreated = repository.findById(budgetId);
            if (budgetCreated.isPresent()) {
                Budget budgetUpgrade = WrapperBudget.budgetFrom(budget.getBudget());
                Budget budgetUpdate = repository.save(WrapperBudget.budgetUpdateFrom(budgetCreated.get(), budgetUpgrade));
                response.setMessage("The project has been updated successfully");
                response.setCode(HttpStatus.OK.toString());
                response.setProjectList(List.of(new Budgets(
                        budgetUpdate.getId(),
                        budgetUpdate.getDocumentId(),
                        Date.from(budgetUpdate.getUpdatedAt().toInstant()))));
            }
        } catch (Exception exception) {
            return new BudgetResponse("Error::Updating project::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
        }
        return response;
    }

    public BudgetResponse removeBudget(Long budgetId) {
        BudgetResponse response =new BudgetResponse();
        try {
            Optional<Budget> budget = repository.findById(budgetId);
            if (budget.isPresent()) {
                repository.delete(budget.get());
                response.setMessage("The budget has been removed successfully");
                response.setCode(HttpStatus.OK.toString());
                response.setProjectList(List.of(new Budgets(
                        budget.get().getId(),
                        budget.get().getDocumentId(),
                        Date.from(budget.get().getUpdatedAt().toInstant()))));
            } else {
                response.setMessage("Don't found budgetId with id "+ budgetId);
                response.setCode(HttpStatus.OK.toString());
                response.setProjectList(null);
            }
        } catch (Exception exception) {
            return new BudgetResponse("Error::Removing project::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
        }
        return response;
    }
}
