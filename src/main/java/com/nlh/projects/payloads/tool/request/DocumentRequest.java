package com.nlh.projects.payloads.tool.request;


import com.nlh.projects.models.tool.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequest {
    private Document document;
}
