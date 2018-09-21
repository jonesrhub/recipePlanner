package com.service.demo.model;

import com.service.demo.model.Classifier.FoodCategoryMapper;
import org.jsoup.Connection;
import org.junit.Test;

import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.io.IOException;

import static org.junit.Assert.*;

public class HtmlScraperTest {

    HtmlScraper scraper;
    private String dummyUrl = "https://www.bbc.com";
    private Connection jsoupConnection;
    private Integer timeout = 10000;
    private String proxy = "proxy.cambridge.office.worldpay.com";
    private Integer proxy_port = 8080;
    @Autowired
    private JsoupConfigurationProperties properties;
    @Mock
    FoodCategoryMapper foodCategoryMapper;

    @Before
    public void setup(){
        scraper = new HtmlScraper(foodCategoryMapper);
    }

    @Test
    public void scraperCorrectlyGeneratesNewRecipe() throws IOException {
        when(scraper.scrapeText(dummyUrl)).thenReturn(new Recipe());
    }

    @Test
    public void scraperCorrectlyGeneratesJsoupConnection() throws IOException {
        scraper.populateJsonConnectionProperties(dummyUrl);
//        Assert.assertEquals(scraper.getTimeout() , timeout);
//        Assert.assertEquals(scraper.getProxy() , proxy);
//        Assert.assertEquals(scraper.getProxyPort() , proxy_port);

    }


    @Test
    public void scrapeTextCreatesAndReturnsRecipeFromURL() throws Exception {

    }

}