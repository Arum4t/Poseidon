package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Moodys Rating is mandatory")
    private String moodysRating;

    @NotBlank(message = "Sand PRating is mandatory")
    private String sandPRating;

    @NotBlank(message = "Fitch Rating is mandatory")
    private String fitchRating;

    @NotNull(message = "must not be null")
    @Pattern(regexp = "^\\d+$",
            message = "can only contain numbers")
    private Integer orderNumber;

    public Rating() {

    }

    public Rating(String moodysRating, String sandPRating, String fitchRating, int i) {
    }
}
