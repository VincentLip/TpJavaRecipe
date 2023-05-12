package org.example.service;

import jdk.jshell.spi.ExecutionControl;
import org.example.dao.IngredientDAO;
import org.example.dao.RecipeDAO;
import org.example.model.Ingredient;
import org.example.model.Recipe;
import org.example.utils.DataBaseSingleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class IngredientService {

    private Connection connection;
    private IngredientDAO ingredientDAO;

    public IngredientService(){
        try{
            connection = DataBaseSingleton.getInstance().getConnection();
            ingredientDAO = new IngredientDAO(connection);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean saveIngredient(String nameIngredient){
        Ingredient ingredient =  Ingredient.builder().nameIngredient(nameIngredient).build();
        try{
            if(ingredientDAO.save(ingredient)){
                return true;
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public Ingredient get(int idIngredient){
        try{
            Ingredient ingredient =  ingredientDAO.get(idIngredient);
            return ingredient;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean updateIngredient(int idIngredient,String nameIngredient){
        try {
            Ingredient ingredient = get(idIngredient);
            ingredient.setNameIngredient(nameIngredient);


            if (ingredientDAO.update(ingredient)) {
                return true;
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean deleteIngredient(int idIngredient){
        Ingredient ingredient= null;
        try{
            ingredient =  ingredientDAO.get(idIngredient);
            if(ingredientDAO.delete(ingredient)){
                return true;
            }
        }catch(SQLException | ExecutionControl.NotImplementedException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Ingredient> getAllIngredients() {
        try {
            return ingredientDAO.get();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
