package com.nlh.projects.models.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

}
