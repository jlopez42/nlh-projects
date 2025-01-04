package com.nlh.projects.repository;

import com.nlh.projects.repository.entity.ProjectOfficerStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectOfficerStaffRepository extends JpaRepository<ProjectOfficerStaff, Integer> {
}
