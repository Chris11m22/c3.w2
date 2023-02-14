package impl;

import com.example.recipeapp.services.FilesService;
import com.example.recipeapp.services.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Ingredients;
import model.Recipe;
import org.springframework.stereotype.Service;
import com.example.recipeapp.services.FilesService;
import com.example.recipeapp.services.RecipeService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    final private FilesService filesService;
    private static Map<Integer, Recipe> recipeMap = new LinkedHashMap<>();
    public static int idRecipe = 0;
    private IngredientService ingredientService;

    public RecipeServiceImpl(FilesService filesService, IngredientService ingredientService) {
        this.filesService = filesService;
        this.ingredientService = ingredientService;
    }

    @PostConstruct
    private void init(){
        readFromFileRec();
    }

    @Override
    public int putNewRecipe(Recipe recipe) {
        recipeMap.put(idRecipe, recipe);
        for(Ingredients ingredients : recipe.getIngredientsList()){
            if(!IngredientServiceImpl.getIngredientsMap().containsValue(ingredients)){
                ingredientService.putNewIngr(ingredients);
            }
        }
        saveToFileRec();
        return idRecipe++;
    }

    @Override
    public Recipe getRecipe(int id) {
        if (recipeMap.get(id) != null) {
            return recipeMap.get(id);
        }
        return null;
    }
    @Override
    public Map<Integer, Recipe> getAllRecipes(){
        return recipeMap;
    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe){
        if (recipeMap.containsKey(id)){
            Recipe oldRecipe = recipeMap.get(id);
            for (Ingredients ingredient : oldRecipe.getIngredientsList()){
                if (IngredientServiceImpl.getIngredientsMap().containsValue(ingredient)){
                    ingredientService.deleteIngr(ingredient);
                }
            }
            recipeMap.put(id, recipe);
            for(Ingredients ingredients : recipe.getIngredientsList()) {
                if (!IngredientServiceImpl.getIngredientsMap().containsValue(ingredients)) {
                    ingredientService.putNewIngr(ingredients);
                }
            }
        }
        saveToFileRec();
        return recipe;
    }
    @Override
    public Path getAllRecFile() throws IOException {
        recipeMap.getOrDefault(idRecipe, null);
        Path path = filesService.createTempFile("allRec");
        for (Recipe recipe : recipeMap.values()) {
            try(Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
                StringBuilder ingredients = new StringBuilder();
                for(Ingredients ingredient : recipe.getIngredientsList()){
                    ingredients.append(ingredient.getName())
                            .append(" ").append(ingredient.getQuantity())
                            .append(" ").append(ingredient.getMeasurement())
                            .append("\n");
                }
                StringBuilder steps = new StringBuilder();
                for (String st : recipe.getSteps()){
                    steps.append("\n").append(st.replace("[", "-"));
                }
                writer.append(recipe.getName()+"\n"
                        +"Время приготовления: "
                        +recipe.getTime()+" мин"+"\n"
                        +"Ингредиенты:"+"\n"
                        +ingredients+"\n"
                        +"Инструкция:"+"\n"
                        +steps);
                writer.append("\n");
            }
        }
        return path;
    }

    @Override
    public boolean deleteRecipe(int id){
        if(recipeMap.containsKey(id)) {
            Recipe recipe =recipeMap.get(id);
            for (Ingredients ingredient : recipe.getIngredientsList()) {
                if (IngredientServiceImpl.getIngredientsMap().containsValue(ingredient)) {
                    ingredientService.deleteIngr(ingredient);
                }
            }
            recipeMap.remove(id);
            return true;
        }
        return false;
    }
    private void saveToFileRec(){
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveToFileRec(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private void readFromFileRec(){
        String json = filesService.readFromFileRec();
        try {
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}