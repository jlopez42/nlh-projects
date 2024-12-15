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
    public ResponseEntity<ProjectResponse> create(@RequestBody ProjectRequest project){
        return ResponseEntity.ok(service.newProject(project));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getProject(id));
    }
    @PutMapping("{id}")
    public ResponseEntity<ProjectResponse> update(@RequestBody ProjectRequest project, @PathVariable Long id){
      return ResponseEntity.ok(service.updateProject(project,id));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<ProjectResponse> remove(@PathVariable Long id){
        return ResponseEntity.ok(service.removeProject(id));
    }

    @GetMapping("list")
    public ResponseEntity<ProjectResponse> listAll(){
        return ResponseEntity.ok(service.list());
    }

}
