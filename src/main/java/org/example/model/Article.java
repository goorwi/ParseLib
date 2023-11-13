package org.example.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Article {
    private String title;
    private String text;
    private String picture;

    public Article(String title, String text, String picture) {
        this.picture = picture;
        this.text = text;
        this.title = title;
        LoadPicture();
    }
    private void LoadPicture() {
        try
        {
            if (this.title.startsWith("REST") || this.title.startsWith("WAF"))
                Thread.sleep(1);
            this.picture.replace(":","");
            BufferedImage image = null;
            URL pic = null;
            if (this.picture != null)
                 pic = new URL(this.picture);
            else throw new Exception("null");

            image = ImageIO.read(pic);
            if (image != null)
            {
                ImageIO.write(image, "jpg", new File("src\\main\\java\\org\\example\\pictures\\"
                        + this.title.substring(0,this.title.indexOf(" "))
                        + this.picture.substring(this.picture.lastIndexOf("."))));
            }
        }
        catch (Exception ex)
        {
            if (ex.getMessage().equals("null"))
                System.out.println("Изображение отсутствует");

        }

    }
}
