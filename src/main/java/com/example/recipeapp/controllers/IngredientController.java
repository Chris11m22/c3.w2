package com.example.recipeapp.controllers;

import impl.IngredientServiceImpl;
import model.Ingredients;
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
    public String addIngredient(@RequestBody Ingredients ingredients ) {
        return ingredientService.addIngredient (ingredients);

    }
}