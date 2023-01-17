package services;

import model.Ingredients;

public interface IngredientService {
    void addIngredient(String nameIngredient, int count, String units);

    Ingredients getIngredient(int id);
}

