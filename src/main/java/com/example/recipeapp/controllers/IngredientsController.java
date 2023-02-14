package com.example.recipeapp.controllers;

import com.example.recipeapp.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.Ingredients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
    @RequestMapping("/ingredient")
    @Tag(name = "ИНГРЕДИЕНТЫ", description = "CRUD-опереации и другие эндпоинты для работы с ингредиентами")
    public class IngredientsController {
        private final IngredientService ingredientService;

    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @PostMapping()
        @Operation(summary = "Добавление ингредиента")
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200", description = "Ингредиент был добавлен", content = {
                        @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Ingredients.class)))
                }
                )})
        public ResponseEntity<Ingredients> postNewIngr(@Valid @RequestBody Ingredients newIngredient) {
            Ingredients ingredient = ingredientService.putNewIngr(newIngredient);
            return ResponseEntity.ok().body(ingredient);
        }

        @GetMapping("/{id}")
        @Operation(
                summary = "Поиск ингредиента по id",
                description = "Нужно искать по одному параметру")
        @Parameters(value = {@Parameter(name = "id", example = "1")})
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200", description = "Ингредиент был найден", content = {
                        @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Ingredients.class)))
                }
                )})
        public ResponseEntity<Ingredients> getIngrById(@PathVariable Integer id) {
            Ingredients ingredient = ingredientService.getIngr(id);
            if (ingredient == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ingredient);
        }

        @GetMapping()
        @Operation(summary = "Получение списка всех ингредиентов")
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200", description = "Ингредиенты найдены", content = {
                        @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Ingredients.class)))
                }
                )})
        public ResponseEntity<Map<Integer, Ingredients>> getAllIngr() {
            if (ingredientService.getAllIngr().isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ingredientService.getAllIngr());
        }

        @PutMapping("/{id}")
        @Operation(summary = "Редактирование ингредиента по id")
        @Parameters(value = {@Parameter(name = "id", example = "1")})
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200", description = "Осуществлено редактирование ингредиента", content = {
                        @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Ingredients.class)))
                }
                )})
        public ResponseEntity<Ingredients> editIngrById(@PathVariable Integer id, @Valid @RequestBody Ingredients newIngredient) {
            Ingredients ingredient = ingredientService.editIngr(id, newIngredient);
            if (ingredient == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ingredient);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Удаление ингредиента по id")
        @Parameters(value = {@Parameter(name = "id", example = "1")})
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200", description = "Ингредиент удален", content = {
                        @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Ingredients.class)))
                }
                )})
        public ResponseEntity<Void> deleteIngrById(@PathVariable Integer id) {
            if (ingredientService.deleteIngr(id)) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }
}
