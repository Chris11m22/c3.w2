package com.example.recipeapp.services;

import model.Ingredients;

public interface IngredientService {

    public Ingredients addIngredient(Ingredients ingredients);

    Ingredients getIngredient(int id);
    Ingredients editIngredient (int id, Ingredients ingredients);
    Ingredients deleteIngredient (int id);
}