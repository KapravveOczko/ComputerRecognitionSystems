package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        SgmConverter converter = new SgmConverter();
        converter.fillArticleList("test2");
        converter.showArticleList();

    }
}