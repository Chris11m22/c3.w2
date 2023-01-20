package services;

import model.Ingredients;
import model.Recipe;

import java.util.List;

public interface RecipeService {


    void addRecipe(Recipe recipe);

    Recipe getRecipe(int id);
}