package com.nlh.projects.service;


import com.nlh.projects.payloads.tool.request.DeadLineRequest;
import com.nlh.projects.payloads.tool.response.DeadLineResponse;

public interface DeadLineService {

    public DeadLineResponse newDeadLine(DeadLineRequest request);

    public DeadLineResponse list();

    public DeadLineResponse updateDeadLine(DeadLineRequest deadLineRequest, int deadLineId) ;

    public DeadLineResponse removeDeadLine(Long documentId) ;
}
