package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Json is mandatory")
    private String json;

    @NotBlank(message = "Template is mandatory")
    private String template;

    @NotBlank(message = "SQLStr is mandatory")
    private String sqlStr;

    @NotBlank(message = "SQLPart is mandatory")
    private String sqlPart;

    public RuleName() {

    }

    public RuleName(String ruleName, String description, String json, String template, String sql, String sqlPart) {
    }
}
