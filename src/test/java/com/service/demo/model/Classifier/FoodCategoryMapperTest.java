package com.service.demo.model.Classifier;

import com.service.demo.model.Category;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import static org.junit.Assert.*;

public class FoodCategoryMapperTest {

    FoodCategoryMapper foodCategoryMapper;
    EnumMap<Category, List<String>> training_data;

    @Before
    public void setUp() throws Exception {
        foodCategoryMapper = new FoodCategoryMapper();
       training_data = new EnumMap<>(Category.class);
       List<String> vegetables = new ArrayList<>();
       vegetables.add("mushroom");
       vegetables.add("potato");
       training_data.put(Category.FRESHVEG, vegetables);
    }

    @Test
    public void categoryShouldBeMappedToNewFoodIfFoodExists() throws Exception {
        String exampleFood = "potato";
        assertEquals(Category.FRESHVEG , foodCategoryMapper.mapFoodCategory(exampleFood));
    }

    @Test
    public void categoryShouldNotMappedToNewFoodIfFoodDoesNotExists() throws Exception {
        String exampleFood = "tofu";
        assertEquals(Category.UNKNOWN , foodCategoryMapper.mapFoodCategory(exampleFood));
    }

}