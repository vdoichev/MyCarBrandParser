package com.vdoichev.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public interface IParser {
    static Elements getElementsByHref(String url, String pageTitle) {
        try {
            Document document = Jsoup.connect(url).get();
            if (pageTitle.length() > 0) {
                if (checkPage(document, pageTitle)) return document.select("a");
            } else return document.select("a");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static boolean checkPage(Document document, String title) {
        boolean check = false;
        Elements elements = document.select("h1");
        for (Element element : elements) {
            if (element.text().equals(title)) {
                check = true;
                break;
            }
        }
        return check;
    }

    List<?> enumElements(Elements listElements, String... filter);

}
