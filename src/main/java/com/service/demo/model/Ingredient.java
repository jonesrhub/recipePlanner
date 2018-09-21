package com.service.demo.model;

import javax.inject.Inject;

public class Ingredient implements Comparable<Ingredient> {

    private String itemName;
    @Inject
    private Amount itemAmount;
    private Category category;

    public Ingredient(String name, Amount amount){
        itemName = name;
        itemAmount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Amount getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Amount itemAmount) {
        this.itemAmount = itemAmount;
    }

    public void setItemAmount(double amount) {
        this.itemAmount.setAmount(amount);
    }

    public void updateItemAmount(double amount) {
        this.itemAmount.setAmount(amount);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCategory(String category) {

    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

        if(((Ingredient) obj).getItemName().length() == this.getItemName().length()){
            if(determineHammingDistance(this.getItemName(), ((Ingredient) obj).getItemName())){
                return true;
            }
            return false;
        }

        if(this.getItemName().startsWith(((Ingredient) obj).getItemName())
            || ((Ingredient) obj).getItemName().startsWith(this.getItemName())){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return 1;
    }

    @Override
    public int compareTo(Ingredient o) {
        return 0;
    }

    private boolean determineHammingDistance(String first, String second){
        int lengthOfString = first.length(); // they are both the same length here
        int hammingdistance = 0;

        for(int i = 0; i < lengthOfString; i++){
            if(hammingdistance > 1){
                return false;
            }
            else if(first.charAt(i) != second.charAt(i)){
                hammingdistance+=1;
            }
        }
        return true;
    }

}
