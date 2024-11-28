package com.nlh.projects.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Detail {

    private Long id;
    private String type;
    private Integer quantity;
    private Integer flats;
    private String rawMaterial;
    private String surface;

    private String area1;
    private String area2;
    private String area3;
    private String area4;
    private String area5;
    private String area6;
    private String area7;
}
