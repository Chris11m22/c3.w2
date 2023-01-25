package impl;

import model.Recipe;
import org.springframework.stereotype.Service;
import services.RecipeService;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    public final Map<Integer, Recipe> recipeMap = new LinkedHashMap<>();
    public static int id = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(id++,recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(int id) {
        if (recipeMap.containsKey(id) && id > 0) {
            return recipeMap.get(id);
        } else {
            throw new IllegalArgumentException("Рецепта с данным id не существует.");
        }
    }

    @Override
    public Recipe editRecipe(int id, Recipe newRecipe) {
        return recipeMap.put(id,newRecipe);
    }

    @Override
    public Recipe deleteRecipe(int counter) {
        return recipeMap.remove(id);
    }
}

