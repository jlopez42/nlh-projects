package com.nlh.projects.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    @Size(max = 255)
    private String description;

    @NotBlank
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @NotNull
    private General general;

    @NotNull
    private Detail detail;

    @NotNull
    private Officer officer;

    @NotBlank
    private Extra extra;

    @CreationTimestamp
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date updatedAt;

    @NotBlank
    private Boolean enable;

    @Override
    public String toString() {
        return "Project{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", general=" + general +
                ", detail=" + detail +
                ", officer=" + officer +
                ", extra=" + extra +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
