package com.example.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Recipe {
    @NotBlank
    private String name;
    @Positive
    private int time;
    @NotEmpty
    private List<Ingredients> ingredientsList = new LinkedList<>();
    @NotEmpty
    private List<String> steps = new LinkedList<>();

}