package com.vdoichev;

import com.vdoichev.utils.IParser;
import com.vdoichev.utils.impl.MainParser;
import com.vdoichev.utils.impl.MarkParser;
import org.jsoup.select.Elements;

import java.util.List;

public class Main {
    public static final String MAIN_URL = "https://www.ilcats.ru";
    public static List<MarkParser> marks;

    public static void main(String[] args) {
        MainParser mainParser = new MainParser();
        Elements markElements = IParser.getElementsByHref(Main.MAIN_URL,"");
        if (markElements != null) {
            marks = mainParser.enumElements(markElements, args);
        } else {
            System.out.println("Марка авто за вказаними параметрами не знайдена!");
        }

//        assert marks != null;
//        marks.forEach(System.out::println);
    }

}