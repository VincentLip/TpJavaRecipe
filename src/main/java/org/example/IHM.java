package org.example;

import org.example.service.IngredientService;
import org.example.service.RecipeService;

import java.util.Scanner;

public class IHM {

    Scanner scanner;
    String choix;
    private RecipeService recipeService;
    private IngredientService ingredientService;

    public void start() {
        scanner = new Scanner(System.in);
        do {
            menu();
            choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    createRecipe();
                    break;
                case "2":
                    listRecipe();
                    break;
                case "3":
                    updateRecipe();
                    break;
                case "4":
                    deleteRecipe();
                    break;
                case "5":
                    createIngredient();
                    break;
                case "6":
                    listIngredient();
                    break;
                case "7":
                    updateIngredient();
                    break;
                case "8":
                    deleteIngredient();
                    break;


            }
        } while (!choix.equals("0"));
    }
    private void menu() {
        System.out.println("-------------------------------");
        System.out.println(" TP Recipe ");
        System.out.println("-------------------------------");
        System.out.println();
        System.out.println("1 - Enregistrer une recette ");
        System.out.println("2 - Lister toutes les recettes ");
        System.out.println("3 - Modifier une recette ");
        System.out.println("4 - Supprimer une recette ");
        System.out.println("5 - Enregistrer un ingredient ");
        System.out.println("6 - Lister tous les ingredients ");
        System.out.println("7 - Modifier un ingredient ");
        System.out.println("8 - Supprimer un ingredient ");
        System.out.println("0- Quitter");
    }

    private void createRecipe() {
        System.out.print("Merci de saisir le nom : ");
        String name = scanner.nextLine();
        System.out.print("Merci de saisir le nombre de personne : ");
        int nb = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Merci de saisir la durée : ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        recipeService = new RecipeService();
        if(recipeService.saveRecipe(name, nb, duration)){
            System.out.println("Recette ajoutée");
        }
        else {
            System.out.println("Erreur d'ajout de recette");
        }
    }

    private void listRecipe() {
        recipeService = new RecipeService();
        recipeService.getAllRecipes().forEach(r -> {
            System.out.println(r);
        });
    }

    private void updateRecipe() {
        System.out.print("Merci de saisir l'id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Merci de saisir le nom : ");
        String name = scanner.nextLine();
        System.out.print("Merci de saisir le nombre de personne : ");
        int nb = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Merci de saisir la durée : ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        recipeService = new RecipeService();
        if(recipeService.updateRecipe(id, name, nb, duration)) {
            System.out.println("recette modifiée");
        }
        else {
            System.out.println("Erreur modification");
        }
    }

    private void deleteRecipe() {
        System.out.print("Merci de saisir l'id de la recette : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        recipeService = new RecipeService();
        if(recipeService.deleteRecipe(id)) {
            System.out.println("recette supprimée");
        }
        else {
            System.out.println("Erreur suppression");
        }
    }

    private void createIngredient() {
        System.out.print("Merci de saisir le nom : ");
        String name = scanner.nextLine();
        ingredientService = new IngredientService();
        if(ingredientService.saveIngredient(name)){
            System.out.println("Ingredient ajouté");
        }
        else {
            System.out.println("Erreur d'ajout de l'ingredient ");
        }
    }

    private void listIngredient() {
        ingredientService = new IngredientService();
        ingredientService.getAllIngredients().forEach(i -> {
            System.out.println(i);
        });
    }

    private void updateIngredient() {
        System.out.print("Merci de saisir l'id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Merci de saisir le nom : ");
        String name = scanner.nextLine();
        ingredientService = new IngredientService();
        if(ingredientService.updateIngredient(id, name)) {
            System.out.println("ingredient modifié");
        }
        else {
            System.out.println("Erreur modification");
        }
    }

    private void deleteIngredient() {
        System.out.print("Merci de saisir l'id de l'ingredient : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        ingredientService = new IngredientService();
        if(ingredientService.deleteIngredient(id)) {
            System.out.println("ingredient supprimé");
        }
        else {
            System.out.println("Erreur suppression");
        }
    }

}
