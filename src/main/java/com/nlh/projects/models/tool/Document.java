package com.nlh.projects.models.tool;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @NotBlank
    private Long projectId;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    private String content;

}
