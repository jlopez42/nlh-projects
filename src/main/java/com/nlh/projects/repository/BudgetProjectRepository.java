package com.nlh.projects.repository;

import org.springframework.stereotype.Repository;

public interface BudgetProjectRepository {
    Boolean existsByProjectId(Long projectId);

}
