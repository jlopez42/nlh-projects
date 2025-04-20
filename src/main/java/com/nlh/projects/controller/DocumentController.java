package com.nlh.projects.controller;

import com.nlh.projects.payloads.tool.request.DocumentRequest;
import com.nlh.projects.payloads.tool.response.DocumentResponse;
import com.nlh.projects.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/support/document")
public class DocumentController {


    @Autowired
    private DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<DocumentResponse> createProject(@RequestBody DocumentRequest document){
        return ResponseEntity.ok(service.newDocument(document));
    }

    @GetMapping("/list")
    public ResponseEntity<DocumentResponse> listProject(){
        return ResponseEntity.ok(service.list());
    }

    @PutMapping("/{documentId}")
    public ResponseEntity<DocumentResponse> createProject(@RequestBody DocumentRequest document, @PathVariable int documentId){
        return ResponseEntity.ok(service.updateDocument(document, documentId));
    }

    @RequestMapping(value="/{documentId}", method=RequestMethod.DELETE)
    public ResponseEntity<DocumentResponse> remove(@PathVariable Long documentId){
        return ResponseEntity.ok(service.removeDocument(documentId));
    }
}
