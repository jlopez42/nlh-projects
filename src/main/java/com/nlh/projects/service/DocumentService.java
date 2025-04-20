package com.nlh.projects.service;

import com.nlh.projects.payloads.tool.request.DocumentRequest;
import com.nlh.projects.payloads.tool.response.DocumentResponse;

public interface DocumentService {

    public DocumentResponse newDocument(DocumentRequest request);

    public DocumentResponse list();

    public DocumentResponse updateDocument(DocumentRequest document, int documentId);

    public DocumentResponse removeDocument(Long documentId);
}
