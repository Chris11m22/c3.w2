package com.example.recipeapp.controllers;

import model.Ingredients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IngredientService;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/get")
    public Ingredients getIngredient(@RequestParam int id) {
        return ingredientService.getIngredient(id);
    }

    @GetMapping("/add")
    public ResponseEntity<Ingredients>  addIngredient(@RequestBody Ingredients ingredients ) {
        ingredientService.addIngredient(ingredients);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<Ingredients> editIngredient (@PathVariable int id, @RequestBody Ingredients ingredient) {
        ingredientService.editIngredient(id, ingredient);
        return ResponseEntity.ok().body(ingredient);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Ingredients> deleteIngredient(@PathVariable int id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.ok().build();
    }
}

