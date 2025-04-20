package com.nlh.projects.models.tool;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeadLine {
    @NotBlank
    @NotNull
    private Long projectId;

    @NotBlank
    @NotNull
    private Date proposal;

    @NotBlank
    @NotNull
    private Date ask;

    @NotBlank
    @NotNull
    private Date reply;

}
