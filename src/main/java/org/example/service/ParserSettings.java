package org.example.service;

public abstract class ParserSettings {
    public static String BASE_URL;
    public static String PREFIX;
    protected int startPoint;
    protected int endPoint;
    protected url url;
    public int getStartPoint() {
        return startPoint;
    }
    public int getEndPoint() {
        return endPoint;
    }
    public url getUrlSource() {
        return url;
    }
}
