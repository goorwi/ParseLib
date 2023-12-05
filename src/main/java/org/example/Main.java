package org.example;

import org.example.dramTheatre.DramParser;
import org.example.dramTheatre.DramSettings;
import org.example.spasTheatre.SpasParser;
import org.example.spasTheatre.SpasSettings;
import org.example.habr.HabrParser;
import org.example.habr.HabrSettings;
import org.example.service.Completed;
import org.example.service.NewData;
import org.example.service.ParserWorker;
import org.example.service.url;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int start = 1;
        int end = 1;
        
        switch (menu()) {
            case habr -> {
                try {
                    end = 2;
                    ParserWorker<ArrayList<String>> parser = new ParserWorker<>(new HabrParser());

                    parser.setParserSettings(new HabrSettings(start, end));
                    parser.onCompletedList.add(new Completed());
                    parser.onNewDataList.add(new NewData());

                    parser.Start();
                    Thread.sleep(1000);
                    parser.Abort();
                } catch (Exception thrown) {
                    System.err.println(thrown.getMessage());
                }
            }
            case theatreDram -> {
                try {
                    ParserWorker<ArrayList<String>> parser = new ParserWorker<>(new DramParser());

                    parser.setParserSettings(new DramSettings(start, end));
                    parser.onCompletedList.add(new Completed());
                    parser.onNewDataList.add(new NewData());

                    parser.Start();
                    Thread.sleep(1000);
                    parser.Abort();
                } catch (Exception exception) {
                    System.err.println(exception.getMessage());
                }
            }
            case theatreSpas -> {
                try {
                    ParserWorker<ArrayList<String>> parser = new ParserWorker<>(new SpasParser());

                    parser.setParserSettings(new SpasSettings(start, end));
                    parser.onCompletedList.add(new Completed());
                    parser.onNewDataList.add(new NewData());

                    parser.Start();
                    Thread.sleep(1000);
                    parser.Abort();
                } catch (Exception exception) {
                    System.err.println(exception.getMessage());
                }
            }
        }
    }

    private static url menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("""
                Выберите сайт, с которого нужно скачать информацию:
                1) habr
                2) Драматический театр
                3) Театр на Спасской
                Вариант №""");
        return url.getUrlByInt(Integer.parseInt(scanner.nextLine()));
    }
}