package ru.fssp.odpea.controller;

import java.util.List;

public class OutClass {
    int file;
    Integer read;
    List<String> files;


    public void doLocalClass() {
        int local = 20;
        local = 21;
        int finalLocal = local;
        class SomeLocal {
            String lock;
            private int count;

            public void doLocalMethod() {
//                local = 11;
                count = count + finalLocal;
                System.out.println(file);
                file = 1;
                count = 10;
                System.out.println(count);
                lock = String.valueOf(count);
                count = 11;
                System.out.println(file);
                file = 2;
                lock = "3";

            }
        }
    }
}
