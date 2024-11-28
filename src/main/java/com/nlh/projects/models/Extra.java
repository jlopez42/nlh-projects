package com.nlh.projects.models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Extra {
    private Long id;
    private String additional;
}
