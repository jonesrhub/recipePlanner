package com.service.demo.model;

import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public class RecipeCollection {

    private HashSet<Recipe> listOfRecipes; // map?
    private int size;

    public RecipeCollection(){
        listOfRecipes = new HashSet<>();
    }

    public HashSet<Recipe> getListOfRecipes() {
        return listOfRecipes;
    }

    public void setListOfRecipes(HashSet<Recipe> listOfRecipes) {
        this.listOfRecipes = listOfRecipes;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // contains isnt working
    public boolean addRecipe(Recipe r){
        if(!listOfRecipes.contains(r)) {
            listOfRecipes.add(r);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {

        return false;
    }


    public void removeRecipeByTitle(String title){
        Recipe r = returnRecipeByTitle(title);
        if(r != null){
            listOfRecipes.remove(r);
        }
    }

    public void removeRecipeById(long recipeId){
        Recipe r = returnRecipeById(recipeId);
        if(r != null){
            listOfRecipes.remove(r);
        }
    }

    public Recipe returnRecipeByTitle(String title){
        for(Recipe r : listOfRecipes){
            if(r.getRecipeName().equals(title)){
                return r;
            }
        }
        return null;
    }

    public Recipe returnRecipeById(long id){
        for(Recipe r : listOfRecipes){
            if(r.getRecipeId() == id){
                return r;
            }
        }
        return null;
    }


}
