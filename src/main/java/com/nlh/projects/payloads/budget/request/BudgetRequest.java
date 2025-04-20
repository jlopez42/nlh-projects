package com.nlh.projects.payloads.budget.request;


import com.nlh.projects.models.budget.Budget;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetRequest {

    @NotEmpty
    private Budget budget;
}
