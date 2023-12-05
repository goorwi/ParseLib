package org.example.service;

import org.jsoup.nodes.Document;

public interface Parser<T> {
    T Parse(Document document, OnNewDataHandler handler);
}
