package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "must not be null")
    private Integer curveId;
    @NotNull(message = "must not be null")
    private Double term;
    @NotNull(message = "must not be null")
    private Double value;

    private Timestamp asOfDate;

    private Timestamp creationDate;

    public CurvePoint() {

    }

    public CurvePoint(int i, double v, double v1) {
    }
}
