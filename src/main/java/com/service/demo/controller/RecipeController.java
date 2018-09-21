package com.service.demo.controller;

import com.service.demo.model.FileExporter;
import com.service.demo.model.Recipe;
import com.service.demo.model.RecipeCollection;
import com.service.demo.model.ShoppingList;
import com.service.demo.model.TextScraper;
import com.service.demo.model.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class RecipeController {

    private TextScraper scraper;
    @Autowired
    private FileExporter fileExporter;

    @Autowired
    RecipeCollection recipeCollection;
    @Autowired
    ShoppingList shoppingList;

    @Autowired
    public void setTextScraper(TextScraper scraper){
        this.scraper = scraper;
    }

    public Recipe scrapeTextFromhtml(String url) throws IOException {
        return scraper.scrapeText(url);
    }

    @PostMapping("/addRecipeViaLink")
    public String addRecipeViaLink(@RequestParam(value="link") String link,
                                   ModelMap modelMap){
        try {
            //python
            //Process p = Runtime.getRuntime().exec("python scraper.py link");
            Recipe newRecipe = scrapeTextFromhtml(link);
            boolean addedToCollection = recipeCollection.addRecipe(newRecipe);
            if(addedToCollection){
                // add ingredients to the shopping list.
                newRecipe.getIngredientList().getIngredients().forEach(i -> shoppingList.addIngredients(i));
            }
            System.out.println("New recipe added with Recipe id: " + newRecipe.getRecipeId());
        } catch (IOException e) {
            e.getMessage();
        }

        modelMap.addAttribute("recipes", recipeCollection);
        modelMap.addAttribute("shoppingList", shoppingList.getshoppingIngredients());


        return "displayIngredients2";

    }

    @GetMapping("/displayAll")
    public String displayAll(ModelMap modelMap){
        modelMap.addAttribute("recipes", recipeCollection);
        modelMap.addAttribute("shoppingList", shoppingList.getshoppingIngredients());

        return "displayIngredients2";
    }

    @GetMapping("/getRecipeByTitle")
    public String getRecipeByTitle(@RequestParam(value="name") String name,
                                   ModelMap modelMap){
        Recipe r = recipeCollection.returnRecipeByTitle(name);
        modelMap.addAttribute("displayRecipe", r);

        return "displayRecipe";
    }

    @GetMapping("/getRecipe")
    public String getRecipe(@RequestParam(value="name") String name,
                                   ModelMap modelMap){
        Recipe r = recipeCollection.returnRecipeByTitle(name);
        modelMap.addAttribute("recipes", recipeCollection);
        modelMap.addAttribute("recipeSteps", recipeCollection.getListOfRecipes());

        return "displayIngredients2";
    }

    @PostMapping("/recipes/removeRecipeById")
    public String removeRecipeById(@RequestParam String recipeId
                                    ,ModelMap modelMap){
        Recipe r = getRecipeById(recipeId);
        Long recipeToRemove = r.getRecipeId();
        System.out.println("New recipe added with Recipe id: " + recipeToRemove);
        try {
            shoppingList.removeShoppingIngredientsGivenRecipe(r);
            recipeCollection.removeRecipeById(recipeToRemove);
        } catch (UpdateException e){
            System.out.println("Error updating caused by: " + e.getMessage());
        }

        modelMap.addAttribute("recipes", recipeCollection);
        modelMap.addAttribute("shoppingList", shoppingList.getshoppingIngredients());

        return "displayIngredients2";
    }


    @GetMapping("/recipes/getRecipeById")
    public Recipe getRecipeById(@RequestParam String recipeId){
        return recipeCollection.returnRecipeById(Long.parseLong(recipeId));
    }

    @GetMapping("/exportIngredientsToFile")
    public void exportIngredients() {
    }
}
