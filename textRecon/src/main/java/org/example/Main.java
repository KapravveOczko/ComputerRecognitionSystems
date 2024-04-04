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


/*        converter.fillArticleList("reutTestData");
//        converter.getArticlesFromData();
        List<Article> articlesInUse = converter.getArticleList();

        for (Article article: articlesInUse){
            System.out.println(article.cleanUpWordsList(article.getWordsList()));
            VectorCreator neigh = new VectorCreator();
            neigh.createNeighbourVector(dictionaries.getPoliticians(), dictionaries.getCurrencies(), dictionaries.getGeography(), article.getWordsList());
            neigh.showVector();
            JsonConverter.appendDataToJson("reutTestData.json", neigh.getVector(), neigh.getWordsVector(), article.getPlaces());
        }*/

//        =========================================================================

//        KNN TEST

        ArrayList<DataObject> testTestVectors = new ArrayList<>();
        ArrayList<DataObject> testLearningVectors = new ArrayList<>();

        testLearningVectors = JsonConverter.loadDataFromJson("reutLearningData.json");
        testTestVectors = JsonConverter.loadDataFromJson("reutTestData.json");

//        knn.knn(testLearningVectors, testTestVectors, 10);
        qualityCalculator qualityCalculator = new qualityCalculator(knn.knn(testLearningVectors, testTestVectors, 10));
        qualityCalculator.showQualities();

//        =========================================================================

//        System.out.println("KURWA");
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