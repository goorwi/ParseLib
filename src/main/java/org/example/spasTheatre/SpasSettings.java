package org.example.spasTheatre;

import org.example.service.ParserSettings;

public class SpasSettings extends ParserSettings {
    public SpasSettings(int start, int end) {
        startPoint = start;
        endPoint = end;
        BASE_URL = "https://ekvus-kirov.ru/afisha/";
        PREFIX = "";
    }
}