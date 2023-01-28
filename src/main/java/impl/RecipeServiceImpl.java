package impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Recipe;
import org.springframework.stereotype.Service;
import services.FileService;
import services.RecipeService;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    final private FileService fileService;


    public final Map<Integer, Recipe> recipeMap = new LinkedHashMap<>();
    public static int id = 0;

    public RecipeServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }
    @PostConstruct
    private void init(){

    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(id++,recipe);
        saveToFile();
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
        saveToFile();
        return recipeMap.put(id,newRecipe);
    }

    @Override
    public Recipe deleteRecipe(int counter) {
        return recipeMap.remove(id);
    }
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private void readFromFile(){
        String json = fileService.readFromFile();
        try {
            new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, LinkedHashMap>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}

