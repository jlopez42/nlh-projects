package com.nlh.projects.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class General {
    private Long id;
    private String description;
    private String location;
}
