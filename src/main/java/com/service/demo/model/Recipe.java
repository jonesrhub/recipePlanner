package com.service.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@Component
public class Recipe {

    private String imageAbsUrl;
    private String recipeName;
    private IngredientsSet ingredientList;
    private List<String> recipeSteps;
    private long recipeId;

    public Recipe(){

    }

    public IngredientsSet getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(IngredientsSet ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getImageAbsUrl() {
        return imageAbsUrl;
    }

    public void setImageAbsUrl(String imageAbsUrl) {
        this.imageAbsUrl = imageAbsUrl;
    }
    public List<String> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(List<String> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public IngredientsSet getRecipeIngredientList() {
        return ingredientList;
    }

    public void setRecipeIngredientList(IngredientsSet ingredientList) {
        this.ingredientList = ingredientList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;

        Recipe r = (Recipe) o;
        return Objects.equals(r.recipeId , recipeId);
    }

    @Override
    public int hashCode() {
        return recipeName.hashCode();
    }


    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId() {
        this.recipeId = IdGenerator.getNextId();
    }

}
