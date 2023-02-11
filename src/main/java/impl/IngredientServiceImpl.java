package impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Ingredients;
import org.springframework.stereotype.Service;
import com.example.recipeapp.services.FileService;
import com.example.recipeapp.services.IngredientService;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    final private FileService fileService;

    public final Map<Integer, Ingredients> ingredientsMap = new LinkedHashMap<>();
    public static int id = 0;

    public IngredientServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }
    @PostConstruct
    private void init(){

    }

    @Override
    public Ingredients addIngredient(Ingredients ingredients) {
        ingredientsMap.put(id++, ingredients);
        saveToFile();
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
        saveToFile();
        return ingredientsMap.put(id, ingredients);
    }

    @Override
    public Ingredients deleteIngredient(int id) {
        return ingredientsMap.remove(id);
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientsMap);
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

