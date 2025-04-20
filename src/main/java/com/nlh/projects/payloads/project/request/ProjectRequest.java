package com.nlh.projects.payloads.project.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nlh.projects.models.project.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {
    @JsonProperty("project")
    private Project project;
}
