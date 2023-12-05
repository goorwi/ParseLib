package org.example.habr;

import org.example.service.OnNewDataHandler;
import org.example.service.Parser;
import org.example.model.HabrArticle;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HabrParser implements Parser<ArrayList<HabrArticle>> {
    @Override
    public ArrayList<HabrArticle> Parse(Document document, OnNewDataHandler handler) {
        ArrayList<HabrArticle> list = new ArrayList<>();

        Elements elements = document.selectXpath("//*/article[@class=\"tm-articles-list__item\"]");

        for (Element article : elements) {
            HabrArticle article1 = new HabrArticle(article);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            handler.OnNewData(this, article1);
        }

        return list;
    }
}