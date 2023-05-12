package org.example.model;

import lombok.Builder;

import java.util.List;

@Builder
public class Recipe {

    private int idRecipe;
    private String nameRecipe;
    private int nbPerson;
    private int durationCook;
    private List<Ingredient> ingredientList;

    public int getIdRecipe() {
        return idRecipe;
    }


    public String getNameRecipe() {
        return nameRecipe;
    }


    public int getNbPerson() {
        return nbPerson;
    }

    public int getDurationCook() {
        return durationCook;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public void setNameRecipe(String nameRecipe) {
        this.nameRecipe = nameRecipe;
    }

    public void setNbPerson(int nbPerson) {
        this.nbPerson = nbPerson;
    }

    public void setDurationCook(int durationCook) {
        this.durationCook = durationCook;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "idRecipe=" + idRecipe +
                ", nameRecipe='" + nameRecipe + '\'' +
                ", nbPerson=" + nbPerson +
                ", durationCook=" + durationCook +
                ", ingredientList=" + ingredientList +
                '}';
    }
}
