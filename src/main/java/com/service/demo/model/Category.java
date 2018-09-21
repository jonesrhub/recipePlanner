package com.service.demo.model;

public enum Category {
    FRESHVEG("Fresh Veg", 1),
    HERBANDSPICE("Herbs and Spices", 2),
    FRESHFRUIT("Fresh Fruit", 1),
    MEATFISH("Meat and Fish", 3),
    FRIDGE("Fridge",2),
    FROZEN("Frozen", 4),
    BAKING("Baking", 2),
    HOME("Home", 8),
    ENTS("Entertainment", 7),
    PERSONAL("Personal and Health", 6),
    OUTDOOR("Outdoor", 7),
    CUPBOARD("Cupboard", 5),
    UNKNOWN("unknown", 999);

    private String stringMeaning;
    private int orderPriority;

    Category(String category, int orderPriority) {
        this.stringMeaning = category;
        this.orderPriority = orderPriority;
    }

    public String getStringMeaning() {
        return stringMeaning;
    }

    public void setStringMeaning(String stringMeaning) {
        this.stringMeaning = stringMeaning;
    }

    public int getPriority(){
        return orderPriority;
    }

    private static Category lookup(String category){
        for(Category c : Category.values()) {
            if(c.getStringMeaning().equals(category)){
                return c;
            }
        }
        return null;
    }
}
