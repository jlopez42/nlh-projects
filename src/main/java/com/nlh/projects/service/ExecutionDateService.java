package com.nlh.projects.service;


import com.nlh.projects.payloads.tool.request.ExecutionDateRequest;
import com.nlh.projects.payloads.tool.response.ExecutionDateResponse;

public interface ExecutionDateService {

    public ExecutionDateResponse newExecution(ExecutionDateRequest request) ;

    public ExecutionDateResponse list();

    public ExecutionDateResponse updateExecution(ExecutionDateRequest executionDateRequest, int executionId) ;

    public ExecutionDateResponse removeExecution(Long executionId) ;
}
