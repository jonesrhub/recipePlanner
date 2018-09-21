package com.service.demo.model;

import java.io.IOException;

public interface TextScraper {

    Recipe scrapeText(String urlInput) throws IOException;
}
