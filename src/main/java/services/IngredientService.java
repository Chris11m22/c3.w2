package services;

import model.Ingredients;

public interface IngredientService {

    void addIngredient(Ingredients ingredients);

    Ingredients getIngredient(int id);
}