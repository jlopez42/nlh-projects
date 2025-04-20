package com.nlh.projects.payloads.tool.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ExecutionDateResponse extends MessageResponse{

    private List<Executions> executions;

    public ExecutionDateResponse(String message, String code) {
        super(message, code);
    }

    public ExecutionDateResponse(String message, String code, List<Executions> executions) {
        super(message, code);
        this.executions = executions;
    }

    public ExecutionDateResponse() {
        super();
    }

}
