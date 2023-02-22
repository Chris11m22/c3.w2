package com.example.recipeapp.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Ingredients {
    @NotBlank(message = "name is mandatory")
    private String name;
    @Positive
    private int quantity;
    private String measurement;

}