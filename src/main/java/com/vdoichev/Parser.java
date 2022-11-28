package com.vdoichev;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    static ArrayList<Mark> parseMainPage(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements listElements = document.select("a");
            return elementToMark(listElements, url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Mark> elementToMark(Elements listElements, String url) {
        ArrayList<Mark> marks = new ArrayList<>();
        for (Element element : listElements) {
            assert element.parent() != null;
            if (element.parent().attr("class").equals("CatalogGroup")) {
                marks.add(new Mark(
                        element.text().trim(),
                        url + element.attr("href").trim()));
            }
        }
        return marks;
    }
}
