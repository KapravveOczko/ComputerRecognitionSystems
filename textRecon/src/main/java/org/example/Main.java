package org.example;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        SgmConverter converter = new SgmConverter();
//        converter.getArticlesFromData();
//        converter.showArticleList();
//        System.out.println(converter.getArticleListCount());

        converter.fillArticleList("test2");
        List<Article> articlesInUse = converter.getArticleList();

        for (Article article: articlesInUse){
//            System.out.println(article.getWordsList());
            System.out.println(article.cleanUpWordsList(article.getWordsList()));
//            System.out.println(article.getContent());
        }

//        DictionariesCreator dictionares = new DictionariesCreator();
//        Map<String, String> currencies = dictionares.getCurrencies();
//        System.out.println(currencies.get("dollar"));
    }
}