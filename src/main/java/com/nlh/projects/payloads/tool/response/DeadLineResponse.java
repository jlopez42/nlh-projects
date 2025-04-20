package com.nlh.projects.payloads.tool.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DeadLineResponse extends MessageResponse{

    private List<DeadLines> deadLines;

    public DeadLineResponse(String message, String code) {
        super(message, code);
    }

    public DeadLineResponse(String message, String code, List<DeadLines> deadLines) {
        super(message, code);
        this.deadLines = deadLines;
    }

    public DeadLineResponse() {
        super();
    }

}
