package org.example.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HtmlLoader {
    String url;

    public HtmlLoader(ParserSettings settings) {
        url = settings.BASE_URL + "/" + settings.PREFIX;
    }
    public Document GetSourceByPageId(int id) throws IOException {
        String currentUrl = url.replace("{CurrentId}", Integer.toString(id));
        try {
            Document doc = Jsoup.connect(currentUrl).get();
            return doc;
        }
        catch (Exception exception)
        {
            return null;
        }
    }
}
