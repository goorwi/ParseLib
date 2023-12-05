package org.example.dramTheatre;

import org.example.service.ParserSettings;

public class DramSettings extends ParserSettings{
    public DramSettings(int start, int end) {
        startPoint = start;
        endPoint = end;
        BASE_URL = "https://kirovdramteatr.ru/afisha/";
        PREFIX = "";
    }
}
