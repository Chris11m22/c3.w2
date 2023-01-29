package com.example.recipeapp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import model.Ingredients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IngredientService;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "CRUD операции")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @GetMapping()
    public Ingredients getIngredient(@RequestBody int id) {
        return ingredientService.getIngredient(id);
    }

    @GetMapping()
    public ResponseEntity<Ingredients>  addIngredient(@RequestBody Ingredients ingredients ) {
        ingredientService.addIngredient(ingredients);
        return ResponseEntity.ok().build();
    }
    @PostMapping()
    public ResponseEntity<Ingredients> editIngredient (@PathVariable int id, @RequestBody Ingredients ingredient) {
        ingredientService.editIngredient(id, ingredient);
        return ResponseEntity.ok().body(ingredient);
    }

    @DeleteMapping()
    public ResponseEntity<Ingredients> deleteIngredient(@PathVariable int id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.ok().build();
    }
}

