package com.service.demo.controller;

import com.service.demo.model.Recipe;
import com.service.demo.model.RecipeCollection;
import com.service.demo.model.ShoppingList;
import com.service.demo.model.TextScraper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private RecipeController recipeController;

    @Mock
    TextScraper textScraper;

    @Mock
    RecipeCollection recipeCollection;

    @Mock
    ShoppingList shoppingList;

    @Mock
    Recipe r;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.standaloneSetup(recipeController)
            .build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void displayAll() throws Exception {

    }

    @Test
    public void getRecipeByTitle() throws Exception {
    }

    @Test
    public void getRecipe() throws Exception {
    }

    @Test
    public void removeIngredientsAndRecipeWhenGivenRecipeIdToRemove() throws Exception {
        Recipe r = new Recipe();

        when(r.getRecipeId()).thenReturn(1L);

    }

    @Test
    public void getRecipeById() throws Exception {
    }


    @Test
    public void scrapeTextFromhtml() throws Exception {
    }


    //TODO a assert that the exceptioni s thrown if cannot connect
    @Test
    public void recipeControllerShouldgenerateAnewRecipeUsingScraper() throws Exception {


        // given
        given(textScraper.scrapeText("String"))
            .willReturn(new Recipe());


        mvc.perform(
            post("/addRecipeViaLink")
            .param("link", "https://www.olivemagazine.com/recipes/meat-and-poultry/steak-burgers-with-peppercorn-sauce-dip/"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("displayIngredients2"));

    }

    @Test
    public void addRecipeViaLink() throws Exception {

    }

}