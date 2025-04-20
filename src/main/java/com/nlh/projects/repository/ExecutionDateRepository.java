package com.nlh.projects.repository;

import com.nlh.projects.repository.entity.ProjectSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExecutionDateRepository extends JpaRepository<ProjectSchedule, Long> {
    Optional<ProjectSchedule> findById(Long projectScheduleId);

    Boolean existsByProjectId(Long projectId);
}
