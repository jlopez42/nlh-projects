package com.nlh.projects.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private Long documentId;

    @CreationTimestamp
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date createdAt;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date updatedAt;

}
