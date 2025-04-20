package com.nlh.projects.service.impl;

import com.nlh.projects.payloads.tool.request.DocumentRequest;
import com.nlh.projects.payloads.tool.response.DocumentResponse;
import com.nlh.projects.payloads.tool.response.Documents;
import com.nlh.projects.repository.DocumentationRepository;
import com.nlh.projects.repository.entity.Document;
import com.nlh.projects.service.DocumentService;
import com.nlh.projects.util.WrapperTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentationRepository repository;

    public DocumentServiceImpl(DocumentationRepository repository) {
        this.repository = repository;
    }

    public DocumentResponse newDocument(DocumentRequest request){
        return getDocumentResponse(request, repository);
    }

    private DocumentResponse getDocumentResponse(DocumentRequest request, DocumentationRepository repository) {
        if(!repository.existsByProjectId(request.getDocument().getProjectId())){
            Document document = repository.save(WrapperTool.documentFrom(request.getDocument()));
            return new DocumentResponse("Document has associated to project successfully",
                    HttpStatus.CREATED.toString(),
                    List.of(new Documents(
                            document.getId(),
                            document.getTitle(),
                            Date.from(document.getCreatedAt()))));
        }
        return new DocumentResponse("This document have not been associated",HttpStatus.CONFLICT.toString());
    }

    public DocumentResponse list(){
        List<Document> document = repository.findAll();
        if(!document.isEmpty()) {
            return new DocumentResponse("Document associated",
                    HttpStatus.ACCEPTED.toString(),
                    WrapperTool.convertTo(document));
        }
        return new DocumentResponse("Does not exist documents associated",HttpStatus.NOT_FOUND.toString());
    }

    public DocumentResponse updateDocument(DocumentRequest document, int documentId) {
        DocumentResponse response =new DocumentResponse();
        try {
            Optional<Document> documentCreated = repository.findById((long) documentId);
            if (documentCreated.isPresent()) {
                Document documentUpgrade = WrapperTool.documentFrom(document.getDocument());
                Document documentUpdate = repository.save(WrapperTool.documentUpdateFrom(documentCreated.get(), documentUpgrade));
                response.setMessage("The project has been updated successfully");
                response.setCode(HttpStatus.OK.toString());
                response.setDocuments(List.of(new Documents(
                        documentUpdate.getId(),
                        documentUpdate.getTitle(),
                        Date.from(documentUpdate.getUpdatedAt()))));
            }
        } catch (Exception exception) {
            return new DocumentResponse("Error::Updating document::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
        }
        return response;
    }

    public DocumentResponse removeDocument(Long documentId) {
        DocumentResponse response =new DocumentResponse();
        try {
            Optional<Document> document = repository.findById(documentId);
            if (document.isPresent()) {
                repository.delete(document.get());
                response.setMessage("The document has been removed successfully");
                response.setCode(HttpStatus.OK.toString());
                response.setDocuments(List.of(new Documents(
                        document.get().getId(),
                        document.get().getTitle(),
                        Date.from(document.get().getUpdatedAt()))));
            } else {
                response.setMessage("Don't found document with id "+ documentId);
                response.setCode(HttpStatus.NOT_FOUND.toString());
                response.setDocuments(null);
            }
        } catch (Exception exception) {
            return new DocumentResponse("Error::Removing document::" + exception.getMessage(), HttpStatus.CONFLICT.toString());
        }
        return response;
    }
}
