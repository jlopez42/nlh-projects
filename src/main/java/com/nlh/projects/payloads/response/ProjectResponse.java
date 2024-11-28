package com.nlh.projects.payloads.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProjectResponse extends MessageResponse{
    private Long id;
    private String name;
    private Date createdAt;
}
