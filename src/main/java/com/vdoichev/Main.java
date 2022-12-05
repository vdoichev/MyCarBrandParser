package com.vdoichev;

import com.vdoichev.db.MyDbForAbp;
import com.vdoichev.utils.impl.Parser;
import com.vdoichev.utils.impl.MarkParser;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String MAIN_URL = "https://www.ilcats.ru";
    public static List<MarkParser> marks;

    public static void main(String[] args) {
        if (args.length>0) System.out.println("Умови парсингу - "+ Arrays.toString(args));
        Parser mainParser = new Parser();
        System.out.println("----------------Початок парсингу сторінок---------------");
        marks = mainParser.parseByUrl(MAIN_URL, args);
        System.out.println("----------------Парсинг сторінок завершено---------------");
        if (marks != null) {
            marks.forEach(System.out::println);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Зберегти дані в БД?(Y/N)");
            boolean answer = (scanner.nextLine().trim().equalsIgnoreCase("Y"));
            if (answer) {
                System.out.println("----------------Початок збереження даних в БД---------------");
                MyDbForAbp.saveMarksToDB(marks);
                System.out.println("----------------Збереження даних в БД завершено---------------");
            }
        }
    }
}