package com.service.demo.model;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ShoppingListTest {

    private static final long RECIPE_ID = 1L;

    private Recipe recipe;
    @Mock
    private IngredientsSet ingredientsSet;
    @Mock
    private Ingredient ingredient;

    private Ingredient additionalTestIngredientEgss = new Ingredient("egg" , new Amount());
    private Ingredient additionalTestIngredientFlour = new Ingredient("plain flour" , new Amount());
    private Ingredient testIngredientEgss = new Ingredient("egg" ,new Amount());
    private Ingredient testIngredientFlour = new Ingredient("plain flour" , new Amount());
    private Ingredient testIngredientFlourWithDoubleAmount = new Ingredient("plain flour" , new Amount());
    private Ingredient fractionAmountIngredient = new Ingredient("ground almonds" , new Amount());

    //Class under Test
    private ShoppingList sl;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        recipe = new Recipe();
        sl = new ShoppingList();
        sl.addIngredients(testIngredientEgss);
        sl.addIngredients(testIngredientFlour);


    }
//
//    private HashSet<Ingredient> ingredients = new HashSet<Ingredient>() {
//        {
//            add(new Ingredient("eggs", "2"));
//            add(new Ingredient("plain flour", "400g"));
//        }
//    };
//
//    private HashSet<Ingredient> fraction_ingredients = new HashSet<Ingredient>() {
//        {
//            add(new Ingredient("ground almonds", "¼"));
//        }
//    };
//
//    private HashSet<Ingredient> expected_non_fraction_list = new HashSet<Ingredient>() {
//        {
//            add(new Ingredient("eggs", "4"));
//            add(new Ingredient("plain flour", "250g"));
//        }
//    };

    @Test
    public void addIngredientss() throws Exception {
        sl.addIngredients(testIngredientEgss);
        sl.addIngredients(testIngredientFlour);

        sl.addIngredients(additionalTestIngredientEgss);
        sl.addIngredients(additionalTestIngredientFlour);

        sl.getshoppingIngredients().forEach(i -> System.out.print(i.getItemName() + " " + i.getItemAmount() + " "));

        //assertEquals(sl.getshoppingIngredients(), expected_non_fraction_list);
    }

    @Test
    public void newIngredientWithFractionGetsAddedCorrectly() throws Exception {
        ShoppingList shopping = new ShoppingList();
        shopping.addIngredients(fractionAmountIngredient);
        //assertEquals(shopping.getshoppingIngredients(), fraction_ingredients);
    }

    @Test
    public void newIngredientWhichAlreadyExistsInShoppingListGetsUpdatedCorrectly() throws Exception {
       // sl.addIngredients(new Ingredient("egg" , "2"));
        sl.addIngredients(testIngredientFlourWithDoubleAmount);
       // assertEquals(sl.getshoppingIngredients() , ingredients);
    }

    @Test
    public void getIngredients() throws Exception {

    }

    @Test
    public void removeIngredientsFromRecipeByIdGivenAmount(){
        when(recipe.getIngredientList()).thenReturn(ingredientsSet);
       // when(recipe.getIngredientList().getIngredients()).thenReturn(ingredients);
        when(recipe.getRecipeId()).thenReturn(RECIPE_ID);
        when(recipe.getRecipeName()).thenReturn("Cake");

    }

    private void setupRecipeIngredients(){

    }




}