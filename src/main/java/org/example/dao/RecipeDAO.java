package org.example.dao;

import jdk.jshell.spi.ExecutionControl;
import org.example.model.Recipe;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeDAO extends BaseDAO<Recipe> {
    public RecipeDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Recipe element) throws SQLException {
        request  = "INSERT INTO recipe (nameRecipe, nbPerson, durationCook) VALUES (?, ?, ?)";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getNameRecipe());
        statement.setInt(2, element.getNbPerson());
        statement.setInt(3, element.getDurationCook());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public boolean update(Recipe element) throws SQLException, ExecutionControl.NotImplementedException {
        request = "UPDATE recipe SET nameRecipe = ?, nbPerson = ?, durationCook = ? WHERE idRecipe = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getNameRecipe());
        statement.setInt(2, element.getNbPerson());
        statement.setInt(3, element.getDurationCook());
        statement.setInt(4, element.getIdRecipe());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public boolean delete(Recipe element) throws SQLException, ExecutionControl.NotImplementedException {
        request = "DELETE FROM recipe WHERE idRecipe = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getIdRecipe());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }


    @Override
    public List<Recipe> get() throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        request = "SELECT * FROM recipe";
        try {
            statement = _connection.prepareStatement(request);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                recipes.add(Recipe.builder()
                        .idRecipe(resultSet.getInt("idRecipe"))
                        .nameRecipe(resultSet.getString("nameRecipe"))
                        .nbPerson(resultSet.getInt("nbPerson"))
                        .durationCook(resultSet.getInt("durationCook")).build());
            }
            return recipes;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Recipe get(int id) throws SQLException {
        Recipe recipe = null;
        request = "SELECT * FROM recipe WHERE idRecipe = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            recipe = Recipe.builder()
                    .idRecipe(resultSet.getInt("idRecipe"))
                    .nameRecipe(resultSet.getString("nameRecipe"))
                    .nbPerson(resultSet.getInt("nbPerson"))
                    .durationCook(resultSet.getInt("durationCook")).build();
        }
        return recipe;
    }
}
