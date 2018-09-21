package com.service.demo.model;


import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public class IngredientsSet  {

    //fields
    private HashSet<Ingredient> listOfIngredients;
    private int size;

    //constructor
    public IngredientsSet() {
        listOfIngredients = new HashSet<>();
    }

    public void setIngredients(HashSet<Ingredient> ingredientList) {
        this.listOfIngredients = ingredientList;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }


    public void addIngredients(Ingredient i){
        listOfIngredients.add(i);
    }

    public HashSet<Ingredient> getIngredients() {
        return listOfIngredients;
    }
}
