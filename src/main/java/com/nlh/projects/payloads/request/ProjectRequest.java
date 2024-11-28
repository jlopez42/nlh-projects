package com.nlh.projects.payloads.request;

import com.nlh.projects.models.Project;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectRequest {
    private Project project;
}
