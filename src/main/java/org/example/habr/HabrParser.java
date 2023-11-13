package org.example.habr;

import org.example.Parser;
import org.example.model.Article;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HabrParser implements Parser<ArrayList<String>> {
    @Override
    public ArrayList<String> Parse(Document document)
    {
        ArrayList<String> list = new ArrayList<>();

        Elements elements = document.selectXpath("//*/article");

        for (Element article : elements)
        {
            String title = article.getElementsByTag("h2").first().getElementsByTag("span").text();
            String picture;
            try
            {
                picture = article.getElementsByAttributeValue("class", "tm-article-body tm-article-snippet__lead").first().getElementsByTag("img").first().attr("src").toString();
            }
            catch (Exception exception)
            {
                picture = null;
            }

            StringBuilder text = new StringBuilder();
            if (article.getElementsByTag("p").first() != null)
            {
                article.getElementsByTag("p").first().forEach(elem -> text.append(elem.text()));
            }
            else if (article.getElementsByClass("article-formatted-body article-formatted-body article-formatted-body_version-1").first() != null)
            {
                article.getElementsByClass("article-formatted-body article-formatted-body article-formatted-body_version-1").first().forEach(elem->text.append(elem.text()));
            }

            Article article1 = new Article(title, text.toString(), picture);
        }

        return list;
    }
}