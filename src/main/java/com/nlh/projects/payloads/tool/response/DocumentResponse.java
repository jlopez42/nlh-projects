package com.nlh.projects.payloads.tool.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DocumentResponse extends MessageResponse{

    private List<Documents> documents;

    public DocumentResponse(String message, String code) {
        super(message, code);
    }

    public DocumentResponse(String message, String code, List<Documents> documents) {
        super(message, code);
        this.documents = documents;
    }

    public DocumentResponse() {
        super();
    }

}
