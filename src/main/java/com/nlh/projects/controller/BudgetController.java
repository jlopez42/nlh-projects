package com.nlh.projects.controller;


import com.nlh.projects.payloads.budget.request.BudgetRequest;
import com.nlh.projects.payloads.budget.response.BudgetResponse;
import com.nlh.projects.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private BudgetService service;

    public BudgetController(BudgetService service) {
        this.service = service;
    }

    @PostMapping("create")
    public ResponseEntity<BudgetResponse> createBudget(@RequestBody BudgetRequest budget){
        return ResponseEntity.ok(service.newBudget(budget));
    }

    @GetMapping("list")
    public ResponseEntity<BudgetResponse> listBudget(){
        return ResponseEntity.ok(service.listAllBudget());
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<BudgetResponse> getBudget(@PathVariable Long budgetId){
        return ResponseEntity.ok(service.getBudgetById(budgetId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<BudgetResponse> listBudgetByProjectId(@PathVariable Long projectId){
        return ResponseEntity.ok(service.listByProjectId(projectId));
    }

    @PutMapping("/{budgetId}")
    public ResponseEntity<BudgetResponse> updateBuget(@RequestBody BudgetRequest budget, @PathVariable Long budgetId){
        return ResponseEntity.ok(service.updatedBudget(budget, budgetId));
    }

    @RequestMapping(value="/{budgetId}", method=RequestMethod.DELETE)
    public ResponseEntity<BudgetResponse> removeBudget(@PathVariable Long budgetId){
        return ResponseEntity.ok(service.removeBudget(budgetId));
    }
}
