package org.example.service;

import java.util.ArrayList;

public class NewData implements OnNewDataHandler<ArrayList<String>> {
    @Override
    public void OnNewData(Object sender, ArrayList<String> articles) {
        for (int i = 0; i < articles.size(); i++) {
            System.out.println(articles.get(0).toString());
        }
        System.out.println("Количество элементов: " + articles.size());
    }
}
