package com.example.recipeapp.controllers;

import com.example.recipeapp.services.FilesService;
import com.example.recipeapp.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.Ingredients;
import model.Recipe;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

    @RestController
    @RequestMapping("/files")
    @Tag(name = "ФАЙЛЫ", description = "CRUD-опереации и другие эндпоинты для работы с файлами рецептов")
    public class FilesController {
        private final FilesService filesService;
        private final RecipeService recipeService;

        public FilesController(FilesService filesService, RecipeService recipeService) {
            this.filesService = filesService;
            this.recipeService = recipeService;
        }

        @GetMapping(value = "/exportRecipes", produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "Получение файла рецептов")
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200", description = "Файл доступен", content = {
                        @Content(mediaType = "application/json")
                }
                )})
        public ResponseEntity<InputStreamResource> dowloadDataRecFile() throws FileNotFoundException {
            File file = filesService.getDataRecFile();
            if(file.exists()){
                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentLength(file.length())
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.json\"")
                        .body(resource);
            }else {
                return ResponseEntity.noContent().build();
            }
        }
        @PostMapping(value = "/importRecipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        @Operation(summary = "Добавление файла с рецептами")
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200", description = "Файл с рецептами добавлен", content = {
                        @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))

                }
                )})
        public ResponseEntity<Void> uploadDataRecFile(@RequestParam MultipartFile file){
            filesService.cleanDataFileRec();
            File dataFile = filesService.getDataRecFile();
            try(FileOutputStream fos = new FileOutputStream(dataFile)){
                IOUtils.copy(file.getInputStream(), fos);
                return ResponseEntity. ok().build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        @PostMapping(value = "/importIngredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        @Operation(summary = "Добавление файла с ингредиентами")
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200", description = "Файл с ингредиентами добавлен", content = {
                        @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Ingredients.class)))
                }
                )})
        public ResponseEntity<Void> uploadDataIngrFile(@RequestParam MultipartFile file){
            filesService.cleanDataFileIngr();
            File dataFile = filesService.getDataIngrFile();
            try(FileOutputStream fos = new FileOutputStream(dataFile)){
                IOUtils.copy(file.getInputStream(), fos);
                return ResponseEntity. ok().build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        @GetMapping("//exportRecipesNew")
        @Operation(summary = "Получение файла рецептов")
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200", description = "Файл доступен", content = {
                        @Content(mediaType = "application/json")
                }
                )})
        public ResponseEntity<Object> getAllRecipesFile() {
            try {
                Path path = recipeService.getAllRecFile();
                if (Files.size(path) == 0) {
                    return ResponseEntity.noContent().build();
                }
                InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .contentLength(Files.size(path))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes File.json\"")
                        .body(resource);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(e.toString());
            }
        }
    }
