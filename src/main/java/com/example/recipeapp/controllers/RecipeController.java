package com.example.recipeapp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import model.Recipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.RecipeService;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD операции")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping()
    public Recipe getRecipe(@RequestBody int id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping()
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @PostMapping()
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        recipeService.editRecipe(id, recipe);
        return ResponseEntity.ok().body(recipe);
    }

    @DeleteMapping()
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }
}