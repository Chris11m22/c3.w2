package com.example.recipeapp.controllers;

import model.Recipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.RecipeService;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/get")
    public Recipe getRecipe(@RequestParam int id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping("/add")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        recipeService.editRecipe(id, recipe);
        return ResponseEntity.ok().body(recipe);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }
}