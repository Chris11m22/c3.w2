package com.example.recipeapp.controllers;

import impl.RecipeServiceImpl;
import model.Ingredients;
import model.Recipe;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/get")
    public Recipe getRecipe(@RequestParam int id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping("/add")
    public String addRecipe(@RequestParam String name, @RequestParam int min, @RequestParam List<Ingredients> ingrList, @RequestParam List<String> steps) {
        recipeService.addRecipe(name, min, ingrList, steps);
        return "Рецепт " + name + " добавлен";
    }
}