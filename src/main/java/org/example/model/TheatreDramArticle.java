package org.example.model;

import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class TheatreDramArticle {
    private String title;
    private String date;
    private String duration;
    private String age;
    private String picture;

    public TheatreDramArticle(Element article) {
        ReadHtml(article);
        LoadPictures();
    }

    private void ReadHtml(Element article) {
        Element td3 = article.getElementsByAttributeValue("class", "td3").first();
        Element td1 = td3.getElementsByAttributeValue("class", "td1").first();
        this.title = td1.getElementsByTag("a").first().textNodes().get(0).toString();
        this.date = td1.getElementsByAttributeValue("class", "date_afisha").first().text();
        Element td2 = td3.getElementsByAttributeValue("class", "td2").first();
        this.duration = td2.getElementsByAttributeValue("class", "td1").first().child(0).textNodes().get(0).toString();
        this.age = td2.getElementsByAttributeValue("class", "value_limit").first().text();
        this.picture = article.getElementsByAttributeValue("class", "td2").first()
                .getElementsByTag("img").first().absUrl("src").toString();
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
                ImageIO.write(image, "jpg", new File("src\\main\\java\\org\\example\\picturesTheatre\\"
                        + nameOfPicture
                        + this.picture.substring(this.picture.lastIndexOf("."))));
            }
        } catch (Exception ex) {

        }
    }

    public String getTitle() {
        return this.title;
    }
}