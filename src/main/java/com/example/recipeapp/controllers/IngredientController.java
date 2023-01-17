package com.example.recipeapp.controllers;


import impl.IngredientServiceImpl;
import model.Ingredients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/ingredients")
    public class IngredientController {

    private IngredientServiceImpl ingredientService;

    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

        @GetMapping("/get")
        public Ingredients getIngredient(@RequestParam int id){
            return ingredientService.getIngredient(id);
        }

        @GetMapping("/add")
        public String addIngredient(@RequestParam String name, @RequestParam int count, @RequestParam String units){
            ingredientService.addIngredient(name, count, units);
            return "Ингридиент " + name +" добавлен";

        }
}
