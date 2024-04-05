package org.example;

import org.example.vectorCreating.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DictionariesCreator dictionaries = new DictionariesCreator();
        SgmConverter converter = new SgmConverter();
        JsonConverter jsonConverter = new JsonConverter();
        KNN knn = new KNN();

//        =========================================================================

//        saving articles to json


/*        converter.fillArticleList("routLearningData");
//        converter.getArticlesFromData();
        List<Article> articlesInUse = converter.getArticleList();

        for (Article article: articlesInUse){
            System.out.println(article.cleanUpWordsList(article.getWordsList()));
            VectorCreator vectorCreator = new VectorCreator();
            vectorCreator.createNeighbourVector(dictionaries.getPoliticians(), dictionaries.getCurrencies(), dictionaries.getGeography(), article.getWordsList());
            vectorCreator.showVector();
            JsonConverter.appendDataToJson("routLearningData.json", vectorCreator.getVector(), vectorCreator.getWordsVector(), article.getPlaces());
        }*/

//        =========================================================================

//        KNN TEST


        ArrayList<DataObject> testLearningVectors = JsonConverter.loadDataFromJson("routLearningData.json");
        ArrayList<DataObject> testTestVectors = JsonConverter.loadDataFromJson("reutTestData.json");

        qualityCalculator qualityCalculator = new qualityCalculator(knn.knn(testLearningVectors, testTestVectors, 5));
        qualityCalculator.showQualities();

//        =========================================================================

//        System.out.println("DUPA");
//        Calculator calc = new Calculator();
//        System.out.println(calc.niewiadomskiMethod("",""));
//        System.out.println(calc.niewiadomskiMethod("kot","kotek"));

//        =========================================================================

//        List<Double> v1 = new ArrayList<>();
//        v1.add(0.21);
//        v1.add(5.0);
//        v1.add(0.98);
//
//        List<Double> v2 = new ArrayList<>();
//        v2.add(0.13);
//        v2.add(12.0);
//        v2.add(0.99);
//
//        List<Double> wordComp = new ArrayList<>();
//        wordComp.add(calc.niewiadomskiMethod("kotek", "kot"));
//
//        System.out.println(calc.euclideanMetrics(v1,v2,wordComp));
//        System.out.println(calc.streetMetrics(v1,v2,wordComp));
//        System.out.println(calc.czebyszewMetrics(v1,v2,wordComp));
    }
}