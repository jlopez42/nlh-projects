package com.nlh.projects.repository;

import com.nlh.projects.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findById(Long projectId);

    Boolean existsByName(String name);
}
