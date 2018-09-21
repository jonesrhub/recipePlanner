package com.service.demo.model;

import com.service.demo.model.Classifier.FoodCategoryMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;


import javax.inject.Inject;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class HtmlScraper implements TextScraper {

    private TextScraper scraper;
    private Recipe recipe;
    private Connection jsoupConnection;
    @Inject
    private JsoupConfigurationProperties properties;
    private FoodCategoryMapper foodCategoryMapper;

//    public static void main(String args[]) throws IOException {
//        HtmlScraper sc = new HtmlScraper();
//        sc.scrapeTextUniversal("https://www.bbcgoodfood.com/recipes/blackcurrant-jam");
//    }
    @Inject
    public HtmlScraper(FoodCategoryMapper foodCategoryMapper) {
        this.foodCategoryMapper = foodCategoryMapper;
    }

    public void populateJsonConnectionProperties(String urlInput){
//        jsoupConnection = Jsoup.connect(urlInput);
//        jsoupConnection.timeout(properties.getTimeout());
//        jsoupConnection.proxy(properties.getProxy(),properties.getProxyPort());
        jsoupConnection = Jsoup.connect(urlInput);
        jsoupConnection.timeout(10000);
        jsoupConnection.proxy("proxy.cambridge.office.worldpay.com" , 8080);
    }

    @Override
    public Recipe scrapeText(String urlInput) throws IOException {
        // python version
//            Connection c = null;
//            try{
//                c = Jsoup.connect("https://www.olivemagazine.com/recipes/meat-and-poultry/chicken-and-leek-puff-pie/");
//            } catch (Exception ex){
//                c.timeout(10000);
//                c.proxy("proxy.cambridge.office.worldpay.com" , 8080);
//                c = Jsoup.connect("https://www.olivemagazine.com/recipes/meat-and-poultry/chicken-and-leek-puff-pie/");
//            }
        //Connection c = Jsoup.connect(urlInput);
        //Connection c = Jsoup.connect("https://www.olivemagazine.com/recipes/vegan/temaki-sushi-rice-hand-rolls/");
        //c.timeout(timeout);
        //c.proxy(proxy , proxy_port);
        populateJsonConnectionProperties(urlInput);

        Document dos = jsoupConnection.get();

        recipe = new Recipe();
        recipe.setRecipeId();
        recipe.setRecipeName(dos.title());

        String imageLocation = null;
        Element img = dos.select("[itemprop=image]").first().children().first();
        System.out.print(img);
        if (img != null) {
            imageLocation = img.absUrl("src");
        }
        System.out.print(imageLocation);

        recipe.setImageAbsUrl(imageLocation);
        Elements amounts = dos.select("[itemprop=recipeIngredient]");
        Elements ingredients = dos.select(".list-group__name");

        Elements step = dos.select(".editor-content");
        IngredientsSet ingredientsList = new IngredientsSet();
        for(int i = 0; i < ingredients.size(); i++){
            Amount newAmount  = new Amount();
            Ingredient ingredient = new Ingredient(ingredients.get(i).ownText(), newAmount);
            ingredient.setCategory(foodCategoryMapper.mapFoodCategory(ingredient.getItemName()));
            ingredientsList.addIngredients(ingredient);
            newAmount.setAmount(amounts.get(i).ownText());
        }

        List<String> steps = new LinkedList<>();
        step.forEach(s -> steps.add(s.text()));

        recipe.setRecipeIngredientList(ingredientsList);
        recipe.setRecipeSteps(steps);

        return recipe;
    }

//    public Recipe scrapeTextUniversal(String urlInput) throws IOException {
//        populateJsonConnectionProperties(urlInput);
//
//        Document dos = jsoupConnection.get();
////        recipe.setRecipeId();
////
////        recipe.setRecipeName(dos.title());
//
//        // get div class containing the word ingredients
//        Elements test = dos.select("[class*=\"ingredients\"]");
//        if(test == null){
//            test = dos.select("[class*=\"ingredient\"]");
//        }
//
//        Elements listItems = test.select("[class*=\"item\"]");
//        IngredientsSet ingredientsList = new IngredientsSet();
//
//
//        for(int i = 0; i < listItems.size(); i++){
//            System.out.print(listItems.get(i).select("[content]").text());
//            ingredientsList.addIngredients(new Ingredient( listItems.get(i).select("[itemprop=recipeIngredient]").text(), null));
//        }
//
//        return new Recipe();
//
//    }

}
