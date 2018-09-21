package com.service.demo.model.Classifier;

import com.service.demo.model.Category;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

@Component
public class FoodCategoryMapper {

    private EnumMap<Category,SortedSet<String>> training_data;

    public FoodCategoryMapper(){
        training_data = new EnumMap<>(Category.class);
        loadFreshFruitMappingData();
        loadFreshVegMappingData();
        loadFrozenMappingData();
        loadherbsMappingData();
        loadVegMappingData();
        loadCupboardMappingData();
        loadCupboardBakingMappingData();
        loadFridgeMappingData();
    }

    private void loadFreshVegMappingData(){
        SortedSet<String> meat =new TreeSet<>();
        meat.add("pork");
        meat.add("mince");
        meat.add("beef");
        meat.add("pork");
        meat.add("sausage");
        meat.add("chicken");
        meat.add("lamb");
        meat.add("turkey");
        meat.add("bacon");
        meat.add("ham");
        meat.add("duck");
        meat.add("prosciutto");
        meat.add("salmon");
        meat.add("salami");
        meat.add("tuna");
        meat.add("shrimp");
        meat.add("prawn");
        training_data.put(Category.MEATFISH, meat);
    }

    private void loadFreshFruitMappingData(){
       SortedSet<String> fruit = new TreeSet<>();
        fruit.add("apple");
        fruit.add("banana");
        fruit.add("strawberry");
        fruit.add("pear");
        fruit.add("plum");
        fruit.add("melon");
        fruit.add("grape");
        fruit.add("pineapple");
        fruit.add("lemon");
        fruit.add("lime");
        fruit.add("avacado");
        fruit.add("apricot");
        fruit.add("avocado");
        fruit.add("blackcurrant");
        fruit.add("blueberries");
        fruit.add("cherry");
        fruit.add("clementine");
        fruit.add("coconut");
        fruit.add("cranberries");
        fruit.add("grapefruit");
        fruit.add("kiwi");
        fruit.add("mandarin");
        fruit.add("mango");
        fruit.add("nectarine");
        fruit.add("olive");
        fruit.add("orange");
        fruit.add("peach");
        fruit.add("plum");
        fruit.add("prune");
        fruit.add("raspberries");
        fruit.add("rhubarb");
        training_data.put(Category.FRESHFRUIT, fruit);
    }

    private void loadVegMappingData(){
       SortedSet<String> vegetables =new TreeSet<>();
        vegetables.add("artichoke");
        vegetables.add("asparagus");
        vegetables.add("aubergine");
        vegetables.add("beetroot");
        vegetables.add("bell pepper");
        vegetables.add("broccoli");
        vegetables.add("brussels sprout");
        vegetables.add("cabbage");
        vegetables.add("carrot");
        vegetables.add("chilli");
        vegetables.add("garlic");
        vegetables.add("cauliflower");
        vegetables.add("celery ");
        vegetables.add("corn");
        vegetables.add("courgette");
        vegetables.add("cucumber");
        vegetables.add("green bean");
        vegetables.add("leek");
        vegetables.add("lettuce");
        vegetables.add("mushroom");
        vegetables.add("onion");
        vegetables.add("pea");
        vegetables.add("pepper");
        vegetables.add("potato");
        vegetables.add("pumpkin");
        vegetables.add("radish");
        vegetables.add("spring onion");
        vegetables.add("squash");
        vegetables.add("sweet potato");
        vegetables.add("tomato");
        vegetables.add("zucchini");
        training_data.put(Category.FRESHVEG, vegetables);
    }
    private void loadherbsMappingData() {
       SortedSet<String> spices =new TreeSet<>();
        spices.add("bay Leaves");
        spices.add("peppercorn");
        spices.add("cayenne Papper");
        spices.add("chilli powder");
        spices.add("cinnamon");
        spices.add("cloves");
        spices.add("cumin");
        spices.add("curry powder");
        spices.add("ginger");
        spices.add("salt");
        spices.add("pepper");
        spices.add("nutmeg");
        spices.add("oregano ");
        spices.add("paprika");
        spices.add("pepper flakes");
        spices.add("chilli flakes");
        spices.add("rosemary");
        spices.add("sesame seed");
        spices.add("thyme");
        spices.add("parsley");
        spices.add("vanilla ");
        spices.add("basil");
        spices.add("cardamon");
        spices.add("chives");
        spices.add("cilantro");
        spices.add("coriander");
        spices.add("Fennel");
        spices.add("dill");
        spices.add("Jalapeno");
        spices.add("mustard");
        spices.add("saffron");
        spices.add("tarragon");
        spices.add("tumeric");
        spices.add("seasoning");
        training_data.put(Category.HERBANDSPICE, spices);
    }

    private void loadFridgeMappingData() {
        SortedSet<String> fridge =new TreeSet<>();
        fridge.add("mayonnaise");
        fridge.add("milk");
        fridge.add("cheese");
        fridge.add("halloumi");
        fridge.add("butter");
        training_data.put(Category.FRIDGE, fridge);
    }

    private void loadFrozenMappingData() {
       SortedSet<String> frozen =new TreeSet<>();
        frozen.add("chips");
        frozen.add("pizza");
        frozen.add("garlic bread");
        frozen.add("ice cream");
        frozen.add("fish fingers");
        frozen.add("chicken nuggets");
        frozen.add("chicken");
        training_data.put(Category.FROZEN, frozen);
    }

    private void loadCupboardMappingData() {
       SortedSet<String> cupboard =new TreeSet<>();
        cupboard.add("oil");
        cupboard.add("chopped tomato");
        cupboard.add("beans");
        cupboard.add("pea");
        cupboard.add("sweetcorn");
        cupboard.add("gravy");
        cupboard.add("stock");
        cupboard.add("rice");
        cupboard.add("pasta");
        cupboard.add("vinegar");
        cupboard.add("chocolate");
        cupboard.add("bun");
        cupboard.add("bread");
        training_data.put(Category.CUPBOARD, cupboard);
    }

    private void loadCupboardBakingMappingData() {
        SortedSet<String> cupboard =new TreeSet<>();
        cupboard.add("sugar");
        cupboard.add("extract");
        cupboard.add("baking powder");
        cupboard.add("flour");
        cupboard.add("egg");
        cupboard.add("cornstarch");
        cupboard.add("yeast");
        cupboard.add("syrup");
        training_data.put(Category.CUPBOARD, cupboard);
    }

    //TODO rest

    public Category mapFoodCategory(String food) {
        // find any matching word to use for categorising
        String[] splitString = food.split("\\s+");
        for (String s : splitString) {
            if (trainingDataContainsFood(s)) {
                for (Map.Entry<Category,SortedSet<String>> entry : training_data.entrySet()) {
                    for(String values : entry.getValue()) {
                        if(values.equals(s)){
                            return entry.getKey();
                        }
                    }
                }
            }
        }
        return Category.UNKNOWN;
    }

    private boolean trainingDataContainsFood(String food){
        for(Map.Entry<Category,SortedSet<String>> entry : training_data.entrySet()){
            if(entry.getValue().contains(food.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}