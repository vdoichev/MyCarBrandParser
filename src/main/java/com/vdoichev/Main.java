package com.vdoichev;

import com.vdoichev.db.MyDbForAbp;
import com.vdoichev.utils.impl.Parser;
import com.vdoichev.utils.impl.MarkParser;

import java.util.List;

public class Main {
    public static final String MAIN_URL = "https://www.ilcats.ru";
    public static List<MarkParser> marks;

    public static void main(String[] args) {
        Parser mainParser = new Parser();
        marks = mainParser.parseByUrl(MAIN_URL, args);
        if (marks !=null) {
            marks.forEach(System.out::println);
            addObjectToDb(marks);
        }
    }

    private static void addObjectToDb(List<MarkParser> marks){
        MyDbForAbp myDbForAbp = new MyDbForAbp();
        marks.forEach(myDbForAbp::addMark);
    }
}