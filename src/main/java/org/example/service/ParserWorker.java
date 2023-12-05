package org.example.service;

import org.example.dramTheatre.DramParser;
import org.example.spasTheatre.SpasParser;
import org.example.habr.HabrParser;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class ParserWorker<T> {
    private Parser<T> parser;
    private ParserSettings parserSettings;
    private HtmlLoader loader;
    boolean isActive;
    public ArrayList<OnCompleted> onCompletedList = new ArrayList<>();
    public ArrayList<OnNewDataHandler> onNewDataList = new ArrayList<>();

    public ParserWorker(HabrParser habrParser) {
        this.parser = (Parser<T>) habrParser;
    }

    public ParserWorker(SpasParser spasParser) {
        this.parser = (Parser<T>) spasParser;
    }

    public ParserWorker(DramParser dramParser) {
        this.parser = (Parser<T>) dramParser;
    }

    public void Start() throws IOException {
        isActive = true;
        Worker();
    }

    public void Abort() {
        isActive = false;
    }

    private void Worker() throws IOException {

        for (int i = parserSettings.getStartPoint(); i <= parserSettings.getEndPoint(); i++) {
            if (!isActive) {
                onCompletedList.get(0).OnCompleted(this);
                return;
            }
            Document document = loader.GetSourceByPageId(i);
            if (document == null) continue;

            T result = parser.Parse(document, onNewDataList.get(0));
            //onNewDataList.get(0).OnNewData(this, result);
        }
        onCompletedList.get(0).OnCompleted(this);
        isActive = false;
    }

    public void setParserSettings(ParserSettings parserSettings) {
        this.parserSettings = parserSettings;
        this.loader = new HtmlLoader(parserSettings);
    }

    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }

    public HtmlLoader getLoader() {
        return loader;
    }

    public ParserSettings getParserSettings() {
        return parserSettings;
    }

    public Parser<T> getParser() {
        return parser;
    }
}
