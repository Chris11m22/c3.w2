package com.example.recipeapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping
    public String start() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String info() {
        return "имя ученика: Кристина\n" +
                "название проекта: MavenTest\n" +
                "дату создания проекта: 05.01.2023\n" +
                "описание проекта: Первое веб приложение";
    }
}
