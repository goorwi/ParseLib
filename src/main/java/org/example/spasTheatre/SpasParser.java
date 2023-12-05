package org.example.spasTheatre;

import lombok.val;
import org.example.service.OnNewDataHandler;
import org.example.service.Parser;
import org.example.model.TheatreSpasArticle;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class SpasParser implements Parser<ArrayList<String>> {
    @Override
    public ArrayList<String> Parse(Document document, OnNewDataHandler handler) {
        ArrayList<String> list = new ArrayList<>();

        Elements elements = document.selectXpath("//tr");
        elements.removeIf(elem -> elem.children().stream().count() != 4);

        for (Element article : elements) {
            val article1 = new TheatreSpasArticle(article);
            list.add(article1.getTitle());
        }

        return list;
    }
}
