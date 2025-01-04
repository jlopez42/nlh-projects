package com.nlh.projects.models;

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
public class OfficerStaff {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    private Long typeStaffId;

}
