package org.example.model;

import lombok.Getter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TheatreSpasArticle {
    @Getter
    private String title;
    private String date;
    private String duration;
    private String age;
    private String picture;

    public TheatreSpasArticle(Element article) {
        ReadHtml(article);
        LoadPictures();
    }

    private void ReadHtml(Element article) {
        Element td2 = article.children().get(1);
        this.title = td2.getElementsByTag("a").first()
                .textNodes().get(0).text();

        Element td1 = article.children().first();
        this.date = td1.getElementsByTag("a").first()
                .getElementsByTag("font").first().text();

        try {
            Document nextPage = getDocument(td2.getElementsByTag("a").first()
                    .absUrl("href").toString());
            Element picture = nextPage.selectXpath("//*[@id=\"photo_osnova\"]").first();
            this.picture = picture.absUrl("src");

            Element duration = nextPage.selectXpath("//div[@class=\"page_box\"]/div").get(1);
            String durationText = duration.textNodes().get(0).text();
            this.duration = durationText.substring(durationText.indexOf(":") + 2);

            Element age = nextPage.selectXpath("//span[@class=\"age_limit\"]").first();
            this.age = age.text();
        } catch (Exception ignored) {

        }
    }

    private void LoadPictures() {
        try {
            String nameOfPicture = this.title.replace(":", "");
            BufferedImage image = null;
            URL pic = null;
            if (this.picture != null)
                pic = new URL(this.picture);
            else throw new Exception("null");

            image = ImageIO.read(pic);
            if (image != null) {
                ImageIO.write(image, "jpg", new File("src\\main\\java\\org\\example\\picturesSpasTheatre\\"
                        + nameOfPicture
                        + this.picture.substring(this.picture.lastIndexOf("."))));
            }
        } catch (Exception ex) {

        }
    }

    private Document getDocument(String url) throws IOException {
        Connection connection = Jsoup.connect(url);
        return connection.get();
    }
}
