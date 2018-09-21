package com.service.demo.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class ShoppingList {

    private HashSet<Ingredient> shoppingIngredients;
    private int size;

    public ShoppingList() {
        shoppingIngredients = new HashSet<>();
    }

    public void setshoppingIngredients(HashSet<Ingredient> ingredientList) {
        this.shoppingIngredients = ingredientList;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }


    public List<Ingredient> getshoppingIngredients() {
        return shoppingIngredients.stream()
            .sorted(Comparator.comparing(ingredient -> ingredient.getCategory().getStringMeaning()))
            .collect(Collectors.toList());
    }

    /**
     * Removes the ingredients from the shopping list which are included in the recipe passed in
     * @param recipe
     * @throws ShoppingListUpdateException
     */
    public void removeShoppingIngredientsGivenRecipe(Recipe recipe) throws ShoppingListUpdateException {
        List<Ingredient> itemsToRemove = new ArrayList<>();
        recipe.getIngredientList().getIngredients().forEach(recipeIngredient -> {
            shoppingIngredients.forEach(shoppingIngredient -> {
                if (recipeIngredient.getItemName() == shoppingIngredient.getItemName()
                    && recipeIngredient.getItemAmount().getUnits() == shoppingIngredient.getItemAmount().getUnits()) {
                    try {
                        if(shouldRemoveItemAfterdeductIngredientAmountByInputAmount(recipeIngredient.getItemAmount().getAmount(), shoppingIngredient)){
                            itemsToRemove.add(shoppingIngredient);
                        }
                    } catch (UpdateException e) {
                        e.getMessage();
                    }
                }
            });
        });
        shoppingIngredients.removeAll(itemsToRemove);
    }

    /**
     * Adds the ingredient to the shopping list.
     * If the ingredient with the same name and unit already exists then the amount is updated, otherwise a new ingredient is added.
     * Fractions are also converted from the unicode to a double.
     * @param newIngredient
     */
    public void addIngredients(Ingredient newIngredient) {
        Iterator it = shoppingIngredients.iterator();
        if (shoppingIngredients.contains(newIngredient)) {
            while (it.hasNext()) {
                Ingredient currIng = (Ingredient) it.next();
                if(currIng.getItemAmount().getUnits() == null
                    || newIngredient.getItemAmount().getUnits() == null){
                    shoppingIngredients.add(newIngredient);
                } else {

                    if (currIng.equals(newIngredient)) {
                        // check if the units are the same
                        if(compareBothIngredientUnits(currIng, newIngredient)){
                            // units are the same so add newingredient amount to current
                            // if we are a fraction convert to double. and then save it
                            if(isIngredientFraction(newIngredient)){
                                convertAndAddNewAmount(newIngredient);
                                currIng.setItemAmount(newIngredient.getItemAmount());
                            } else {
                                double newAmount = currIng.getItemAmount().getAmount() + newIngredient.getItemAmount().getAmount();
                                currIng.updateItemAmount(newAmount);
                            }
                        } else {
                            // units not the same so just add separatley
                            shoppingIngredients.add(newIngredient);
                        }
                    }
                }
            }
        } else {
            if(isIngredientFraction(newIngredient)) {
                convertAndAddNewAmount(newIngredient);
            }
            shoppingIngredients.add(newIngredient);

        }
    }

    private boolean isIngredientFraction(Ingredient ingredient){
        List<Integer> characterValues = ingredient.getItemAmount().getUnits().chars().boxed().collect(Collectors.toList());
        return characterValues.contains(Measure.HALF.getUnicodeValue()) || characterValues.contains(Measure.QUARTER.getUnicodeValue());
    }

    private boolean compareBothIngredientUnits(Ingredient original, Ingredient newIngredient){
        String originalAmount = original.getItemAmount().getUnits();
        String newAmount = newIngredient.getItemAmount().getUnits();

        return originalAmount.toUpperCase().equals(newAmount.toUpperCase());
    }

    private void convertAndAddNewAmount(Ingredient ingredientAmount){
        List<Integer> characterValues = ingredientAmount.getItemAmount().getUnits().chars().boxed().collect(Collectors.toList());
        double newAmount = 0;
        for (Integer i : characterValues) {
            newAmount = i == Measure.HALF.getUnicodeValue() ? Measure.HALF.getIntegerValue() : Measure.QUARTER.getIntegerValue(); // assume that if its not, is tehre something else that it could be?
            ingredientAmount.setItemAmount(newAmount);
        }
    }

    private boolean shouldRemoveItemAfterdeductIngredientAmountByInputAmount(double amountToDeduct, Ingredient ingredient) throws UpdateException {
        return (removeIngredientAmountFromIngredient(amountToDeduct, ingredient));
    }

    private Boolean removeIngredientAmountFromIngredient(double amountToDeduct, Ingredient ingredient) {
        double newAmount = (ingredient.getItemAmount().getAmount() - amountToDeduct);
        if (newAmount > 0) {
            ingredient.setItemAmount(newAmount);
            return false;
        } else {
            return true;
        }
    }


}