package com.vdoichev;

import com.vdoichev.utils.impl.MainParser;
import com.vdoichev.utils.impl.MarkParser;

import java.util.List;

public class Main {
    public static final String MAIN_URL = "https://www.ilcats.ru";
    public static List<MarkParser> marks;

    public static void main(String[] args) {
        MainParser mainParser = new MainParser();
        marks = mainParser.parseByUrl(MAIN_URL, args);

        marks.forEach(System.out::println);
    }

}