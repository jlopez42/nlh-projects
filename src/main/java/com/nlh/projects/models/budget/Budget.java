package com.nlh.projects.models.budget;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    @NotEmpty
    private Long projectId;

    @NotEmpty
    private Long documentId;

}
