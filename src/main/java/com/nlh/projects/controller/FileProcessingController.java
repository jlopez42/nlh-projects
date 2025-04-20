package com.nlh.projects.controller;

import com.nlh.projects.service.impl.FileProcessingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/support/file")
public class FileProcessingController {

    @Autowired
    private FileProcessingServiceImpl fileProcessingServiceImpl;

    @GetMapping("/list")
    public ResponseEntity<?> getFileList() {
        return new ResponseEntity<>(fileProcessingServiceImpl.fileList(), HttpStatus.OK);
    }

    @GetMapping(value = "/download/{name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> downloadFile(@PathVariable(value = "name") String fileName) {
        Resource file = fileProcessingServiceImpl.downloadFile(fileName);
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(file);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file) {
        String status = fileProcessingServiceImpl.uploadFile(file);
        return "CREATED".equals(status) ? new ResponseEntity<>(HttpStatus.CREATED) : ("EXIST".equals(status) ? new ResponseEntity<>(HttpStatus.NOT_MODIFIED) : new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
    }
}
