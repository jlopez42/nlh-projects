package com.nlh.projects.service.impl;

import com.nlh.projects.repository.DocumentationRepository;
import com.nlh.projects.service.FileProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileProcessingServiceImpl implements FileProcessingService {

    @Value("${file.path}")
    private String basePath;

    @Autowired
    private DocumentationRepository repository;

    public List<String> fileList() {
        File dir = new File(basePath);
        File[] files = dir.listFiles();

        return files != null ? Arrays.stream(files).map(File::getName).collect(Collectors.toList()) : null;
    }


    public String uploadFile(MultipartFile multipartFile) {
        File dir = new File(basePath + multipartFile.getName());

        if (dir.exists()) {
            return "EXIST";
        }

        Path path = Path.of(basePath + multipartFile.getName());

        try {
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return "CREATED";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "FAILED";
    }

    public Resource downloadFile(String fileName) {
        File dir = new File(basePath + fileName);
        try {
            if (dir.exists()) {
                return new UrlResource(dir.toURI());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

}
