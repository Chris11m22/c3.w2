package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import services.IngredientService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


    @Data
    @AllArgsConstructor
    public class Recipe {
        private String nameRecipe;
        private int minutes;
        private List<Ingredients> ingredientsList;
        private List<String> steps;

    }

