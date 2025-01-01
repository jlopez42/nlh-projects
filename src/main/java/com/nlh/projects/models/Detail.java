package com.nlh.projects.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project_details")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String type;
    private Integer quantity;
    private Integer flats;
    @NotBlank
    private String rawMaterial;
    @NotBlank
    private String surface;
    @OneToMany(mappedBy = "detail")
    private List<Area> areas;
    @OneToOne(mappedBy = "detail")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "project_id")
    private Project project1;

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", flats=" + flats +
                ", rawMaterial='" + rawMaterial + '\'' +
                ", surface='" + surface + '\'' +
                ", areas=" + areas +
                ", project=" + project +
                '}';
    }
}
