package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;
/**
 * Model of curvePoint
 *
 * @author Quentin
 *
 */
@Entity
@Data
@Table(name = "curvepoint")
public class CurvePoint {

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
