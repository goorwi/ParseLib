package org.example;

public abstract class ParserSettings {
    public static String BASE_URL;
    public static String PREFIX;
    protected int startPoint;
    protected int endPoint;
    public int getStartPoint() {
        return startPoint;
    }
    public int getEndPoint() {
        return endPoint;
    }
}
