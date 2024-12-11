package com.nlh.projects.controller;

import com.nlh.projects.payloads.request.ProjectRequest;
import com.nlh.projects.payloads.response.ProjectResponse;
import com.nlh.projects.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping("create")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest project){
        return ResponseEntity.ok(service.newProject(project));
    }

    @GetMapping("list")
    public ResponseEntity<ProjectResponse> listProjects(){
        return ResponseEntity.ok(service.list());
    }

}
