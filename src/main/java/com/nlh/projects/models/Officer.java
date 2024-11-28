package com.nlh.projects.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Officer {

    private Long id;
    private String customer1;
    private String customer2;
    private String professional1;
    private String professional2;
    private String specialist1;
    private String specialist2;
    private String contact;
}
