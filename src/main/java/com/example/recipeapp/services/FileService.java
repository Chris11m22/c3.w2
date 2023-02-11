package com.example.recipeapp.services;

import java.io.File;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanData();
}
