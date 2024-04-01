package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        SgmConverter converter = new SgmConverter();
//
//        converter.fillArticleList("test2");
//        List<Article> articlesInUse = converter.getArticleList();
//
//        for (Article article: articlesInUse){
//            System.out.println(article.cleanUpWordsList(article.getWordsList()));
//        }


//        =========================================================================

        Calculator calc = new Calculator();
//        System.out.println(calc.niewiadomskiMethod("kotek","kot"));
//        System.out.println(calc.niewiadomskiMethod("kot","kotek"));

//        =========================================================================

        List<Double> v1 = new ArrayList<>();
        v1.add(0.21);
        v1.add(5.0);
        v1.add(0.98);

        List<Double> v2 = new ArrayList<>();
        v2.add(0.13);
        v2.add(12.0);
        v2.add(0.99);

        List<Double> wordComp = new ArrayList<>();
        wordComp.add(calc.niewiadomskiMethod("kotek", "kot"));

        System.out.println(calc.euclideanMetrics(v1,v2,wordComp));
        System.out.println(calc.streetMetrics(v1,v2,wordComp));
        System.out.println(calc.czebyszewMetrics(v1,v2,wordComp));
    }
}