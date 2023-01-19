package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
    private String nameRecipe;
    private int minutes;
    private List<Ingredients> ingredientsList;
    private List<String> steps;

}
