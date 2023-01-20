package com.example.recipeapp.controllers;

import impl.RecipeServiceImpl;
import model.Ingredients;
import model.Recipe;
import org.springframework.web.bind.annotation.*;
import services.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/get")
    public Recipe getRecipe(@RequestParam int id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping("/add")
    public String addRecipe(@RequestBody Recipe recipe) {
        return  recipeService.addRecipe(recipe) ;
    }
}