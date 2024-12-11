package com.nlh.projects.util;

import com.nlh.projects.models.Project;
import com.nlh.projects.payloads.response.Projects;

import java.util.ArrayList;
import java.util.List;

public abstract class WrapperProject {

    public static List<Projects> convertTo (List<Project> projectList){
        List<Projects> projects = new ArrayList<>();
        projectList.forEach(project -> projects.add(new Projects(project.getId(), project.getName(), project.getCreatedAt())));
        return projects;
    }
}
