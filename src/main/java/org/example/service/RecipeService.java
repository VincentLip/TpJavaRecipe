package org.example.service;

import jdk.jshell.spi.ExecutionControl;
import org.example.dao.RecipeDAO;
import org.example.model.Recipe;
import org.example.utils.DataBaseSingleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RecipeService {

    private Connection connection;
    private RecipeDAO recipeDAO;

    public RecipeService(){
        try{
            connection = DataBaseSingleton.getInstance().getConnection();
            recipeDAO = new RecipeDAO(connection);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean saveRecipe(String nameRecipe,int nbPerson,int durationCook){
        Recipe recipe =  Recipe.builder().nameRecipe(nameRecipe).nbPerson(nbPerson).durationCook(durationCook).build();
        try{
            if(recipeDAO.save(recipe)){
                return true;
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public Recipe get(int idRecipe){
        try{
            Recipe recipe =  recipeDAO.get(idRecipe);
            return recipe;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean updateRecipe(int idRecipe,String nameRecipe,int nbPerson,int durationCook){
        try {
            Recipe recipe = get(idRecipe);
            recipe.setNameRecipe(nameRecipe);
            recipe.setNbPerson(nbPerson);
            recipe.setDurationCook(durationCook);

            if (recipeDAO.update(recipe)) {
                return true;
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean deleteRecipe(int idRecipe){
        Recipe recipe= null;
        try{
            recipe =  recipeDAO.get(idRecipe);
            if(recipeDAO.delete(recipe)){
                return true;
            }
        }catch(SQLException | ExecutionControl.NotImplementedException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Recipe> getAllRecipes() {
        try {
            return recipeDAO.get();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
