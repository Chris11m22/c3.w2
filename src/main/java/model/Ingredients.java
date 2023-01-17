package model;

import lombok.AllArgsConstructor;
import lombok.Data;

    @Data
    @AllArgsConstructor
    public class Ingredients {
        private String nameIngredient;
        private int count;
        private String units;
    }

