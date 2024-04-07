package org.example;

import org.example.vectorCreating.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        // main.convertsTextsIntoVectorsFiles();
        main.knnOperation();

    }

    private void convertsTextsIntoVectorsFiles() {
        DictionariesCreator dictionaries = new DictionariesCreator();
        SgmConverter converter = new SgmConverter();

        converter.fillArticleList("routLearningData");
        converter.getArticlesFromData();
        ArrayList<Article> articlesInUse = converter.getArticleList();

        for (Article article : articlesInUse) {
            VectorCreator vectorCreator = new VectorCreator();
            vectorCreator.createNeighborVector(dictionaries.getPoliticians(), dictionaries.getCurrencies(), dictionaries.getGeography(), article.getWordsList());
            JsonConverter.appendDataToJson("routLearningDataIPart.json", vectorCreator.getVector(), vectorCreator.getWordsVector(), article.getPlaces());
        }
    }

    private void knnOperation() {
        KNN knn = new KNN();
        ArrayList<DataObject> testLearningVectors = JsonConverter.loadDataFromJson("testLearning9%.json");
        ArrayList<DataObject> testTestVectors = JsonConverter.loadDataFromJson("testData9%.json");

        qualityCalculator qualityCalculator = new qualityCalculator(knn.knn(testLearningVectors, testTestVectors, 3));
        qualityCalculator.showQualities();
    }
}