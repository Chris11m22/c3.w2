package com.example.recipeapp.services;

import model.Ingredients;

import java.util.Map;

public interface IngredientService {

    Ingredients putNewIngr(Ingredients ingredient);
    Ingredients getIngr(Integer id);

    Map<Integer, Ingredients> getAllIngr();

    Ingredients editIngr(Integer id, Ingredients ingredient);

    boolean deleteIngr(Integer id);

    boolean deleteIngr(Ingredients ingredients);
}