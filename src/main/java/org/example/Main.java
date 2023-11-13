package org.example;

import org.example.habr.HabrParser;
import org.example.habr.HabrSettings;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ParserWorker<ArrayList<String>> parser = new ParserWorker<>(new HabrParser());

        int start = 1, end = 2;
        parser.setParserSettings(new HabrSettings(start, end));
        parser.onCompletedList.add(new Completed());
        parser.onNewDataList.add(new NewData());

        parser.Start();
        Thread.sleep(10000);
        parser.Abort();
    }
}