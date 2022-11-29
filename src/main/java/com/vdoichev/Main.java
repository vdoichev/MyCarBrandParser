package com.vdoichev;

import com.vdoichev.objects.Mark;
import com.vdoichev.utils.IParser;
import com.vdoichev.utils.impl.MainParse;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Main {
    public static final String MAIN_URL = "https://www.ilcats.ru";
    public static ArrayList<Mark> marks;

    public static void main(String[] args) {
        MainParse mainParse = new MainParse();
        Elements markElements = IParser.getElementsByHref(Main.MAIN_URL);
        if (markElements != null) {
            marks = mainParse.enumElements(markElements, args);
        }else {
            System.out.println("Марка авто за вказаними параметрами не знайдена!");
        }


        assert marks != null;
        for (Mark mark : marks) {
            System.out.println(mark);
        }
    }

}