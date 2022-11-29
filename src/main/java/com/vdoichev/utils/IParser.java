package com.vdoichev.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public interface IParser {
    static Elements getElementsByHref(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            return document.select("a");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    ArrayList<?> enumElements(Elements listElements, String... filter);

}
