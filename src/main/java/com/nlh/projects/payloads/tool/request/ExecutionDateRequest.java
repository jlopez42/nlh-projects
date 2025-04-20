package com.nlh.projects.payloads.tool.request;


import com.nlh.projects.models.tool.Execution;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionDateRequest {
    private Execution execution;
}
