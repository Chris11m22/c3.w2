package services;

import model.Ingredients;
import model.Recipe;

import java.util.List;

public interface RecipeService {
    void addRecipe(String nameRecipe, int minutes, List<Ingredients> ingredientsList, List<String> steps);

    void addRecipe(String nameRecipe, int minutes, List<Ingredients> ingredientsList, List<String> steps);

    Recipe getRecipe(int id);
}