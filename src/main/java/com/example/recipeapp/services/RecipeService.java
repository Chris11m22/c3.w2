package com.example.recipeapp.services;

import model.Recipe;

public interface RecipeService {


  public Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(int id);
    Recipe editRecipe (int id, Recipe newRecipe);
    Recipe deleteRecipe (int counter);
}