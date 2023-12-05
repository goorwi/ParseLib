package org.example.model;

import lombok.Getter;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

@Getter
public class HabrArticle {
    private String title;
    private String text;
    private String picture;
    private String pathToImage;

    public HabrArticle(Element article) {
        ReadHtml(article);
        LoadPicture();
    }

    private void ReadHtml(Element article) {
        this.title = article.getElementsByTag("h2").first().getElementsByTag("span").text();
        try {
            this.picture = article.getElementsByAttributeValue("class", "tm-article-body tm-article-snippet__lead").first().getElementsByTag("img").first().attr("src").toString();
        } catch (Exception exception) {
            this.picture = null;
        }

        StringBuilder text = new StringBuilder();
        if (article.getElementsByTag("p").first() != null) {
            article.getElementsByTag("p").first().forEach(elem -> text.append(elem.text()));
        } else if (article.getElementsByClass("article-formatted-body article-formatted-body article-formatted-body_version-1").first() != null) {
            article.getElementsByClass("article-formatted-body article-formatted-body article-formatted-body_version-1").first().forEach(elem -> text.append(elem.text()));
        }
        this.text = text.toString();
    }

    private void LoadPicture() {
        try {
            String nameOfPicture = this.title.replace(":", "").substring(0, this.title.indexOf(" "));
            BufferedImage image = null;
            URL pic = null;
            if (this.picture != null)
                pic = new URL(this.picture);
            else throw new Exception("null");

            image = ImageIO.read(pic);
            if (image != null) {
                ImageIO.write(image, "jpg", new File("src\\main\\java\\org\\example\\picturesHabr\\"
                        + nameOfPicture
                        + this.picture.substring(this.picture.lastIndexOf("."))));
                this.pathToImage = "src\\main\\java\\org\\example\\picturesHabr\\"
                        + nameOfPicture
                        + this.picture.substring(this.picture.lastIndexOf("."));
            }
        } catch (Exception ex) {

        }

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(this.title).append(this.picture).append(this.text);
        return res.toString();
    }

    public boolean isNull() {
        return title == null && picture == null && text == null && pathToImage == null;
    }
}
