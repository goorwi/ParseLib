package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

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
            //System.out.println("Страница не найдена");
            return null;
        }
    }
}
