package com.nlh.projects.payloads.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {
    private String message;

    private String code;
}
