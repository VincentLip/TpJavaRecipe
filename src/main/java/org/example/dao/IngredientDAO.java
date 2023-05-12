package org.example.dao;

import jdk.jshell.spi.ExecutionControl;
import org.example.model.Ingredient;
import org.example.model.Recipe;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IngredientDAO extends BaseDAO<Ingredient>{
    public IngredientDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Ingredient element) throws SQLException {
        request  = "INSERT INTO ingredient (nameIngredient) VALUES (?,)";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getNameIngredient());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public boolean update(Ingredient element) throws SQLException, ExecutionControl.NotImplementedException {
        request = "UPDATE ingredient SET nameIngredient = ? WHERE idIngredient = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getNameIngredient());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public boolean delete(Ingredient element) throws SQLException, ExecutionControl.NotImplementedException {
        request = "DELETE FROM ingredient WHERE idIngredient = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getIdIngredient());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public List<Ingredient> get() throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        request = "SELECT * FROM ingredient";
        try {
            statement = _connection.prepareStatement(request);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ingredients.add(Ingredient.builder()
                        .idIngredient(resultSet.getInt("idIngredient"))
                        .nameIngredient(resultSet.getString("nameIngredient")).build());
            }
            return ingredients;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Ingredient get(int id) throws SQLException {
        Ingredient ingredient = null;
        request = "SELECT * FROM ingredient WHERE idIngredient = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            ingredient = Ingredient.builder()
                    .idIngredient(resultSet.getInt("idIngredient"))
                    .nameIngredient(resultSet.getString("nameIngredient")).build();
        }
        return ingredient;
    }
}
