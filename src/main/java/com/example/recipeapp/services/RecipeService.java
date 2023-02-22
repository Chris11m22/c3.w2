package com.example.recipeapp.services;

import com.example.recipeapp.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface RecipeService {


  int putNewRecipe(Recipe recipe);
  Recipe getRecipe(int numberRecipe);

  Map<Integer, Recipe> getAllRecipes();



  Recipe editRecipe(int id, Recipe recipe);

  Path getAllRecFile() throws IOException;

  boolean deleteRecipe(int id);
}