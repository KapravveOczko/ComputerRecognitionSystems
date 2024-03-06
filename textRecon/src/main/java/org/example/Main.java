package org.example;

public class Main {
    public static void main(String[] args) {

        SgmConverter converter = new SgmConverter();
        converter.getArticlesFromData();
//        converter.showArticleList();
        System.out.println(converter.getArticleListCount());

    }
}