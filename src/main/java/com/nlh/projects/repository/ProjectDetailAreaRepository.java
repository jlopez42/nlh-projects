package com.nlh.projects.repository;

import com.nlh.projects.repository.entity.ProjectDetailArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDetailAreaRepository extends JpaRepository<ProjectDetailArea, Integer> {
}
