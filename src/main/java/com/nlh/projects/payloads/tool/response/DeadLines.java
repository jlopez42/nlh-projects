package com.nlh.projects.payloads.tool.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DeadLines {

    private Long id;
    private String title;
    private Date createdAt;

    public DeadLines(Long id, String title, Date createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
    }

}
