package org.example.model;

import lombok.Builder;

@Builder
public class Ingredient {

    private int idIngredient;
    private String nameIngredient;

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "idIngredient=" + idIngredient +
                ", nameIngredient='" + nameIngredient + '\'' +
                '}';
    }
}
