package com.nlh.projects.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Detail {

    @NotBlank
    private String type;

    private Integer quantity;

    private Integer flats;
    @NotBlank
    private String rawMaterial;
    @NotBlank
    private String surface;

    @NotNull
    private List<Area> areas;

    @Override
    public String toString() {
        return "Detail{" +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", flats=" + flats +
                ", rawMaterial='" + rawMaterial + '\'' +
                ", surface='" + surface + '\'' +
                ", areas=" + areas +
                '}';
    }
}
