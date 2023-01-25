package impl;

import model.Ingredients;
import org.springframework.stereotype.Service;
import services.IngredientService;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    public final Map<Integer, Ingredients> ingredientsMap = new LinkedHashMap<>();
    public static int id = 0;


    @Override
    public Ingredients addIngredient(Ingredients ingredients) {
        ingredientsMap.put(id++, ingredients);
        return ingredients;
    }


    @Override
    public Ingredients getIngredient(int id) {
        if (ingredientsMap.containsKey(id) && id > 0) {
            return ingredientsMap.get(id);
        } else {
            throw new IllegalArgumentException("Данного ингредиента не существует.");
        }
    }

    @Override
    public Ingredients editIngredient(int id, Ingredients ingredients) {
        return ingredientsMap.put(id,ingredients);
    }

    @Override
    public Ingredients deleteIngredient(int id) {
        return ingredientsMap.remove(id);
    }
}
