package com.nlh.projects.repository;

import com.nlh.projects.repository.entity.ProjectDeadline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeadLineRepository extends JpaRepository<ProjectDeadline, Long> {
    Optional<ProjectDeadline> findById(Long projectDeadLineId);

    Boolean existsByProjectId(Long projectId);
}
