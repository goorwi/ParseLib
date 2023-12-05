package org.example.dramTheatre;

import org.example.service.OnNewDataHandler;
import org.example.service.Parser;
import org.example.model.TheatreDramArticle;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class DramParser implements Parser<ArrayList<String>> {
    @Override
    public ArrayList<String> Parse(Document document, OnNewDataHandler handler) {
        ArrayList<String> list = new ArrayList<>();

        Elements elements = document.selectXpath("//*[@class=\"t_afisha\"]");

        for (Element article : elements) {
            TheatreDramArticle article1 = new TheatreDramArticle(article);
            list.add(article1.getTitle());
        }
        return list;
    }
}
