package com.vdoichev;

import java.util.ArrayList;

public class Main {
    private static final String MAIN_URL = "https://www.ilcats.ru";
    public static ArrayList<Mark> marks;

    public static void main(String[] args) {
        marks = Parser.parseMainPage(MAIN_URL);



        assert marks != null;
        for (Mark mark : marks) {
            System.out.println(mark);
        }
    }

}