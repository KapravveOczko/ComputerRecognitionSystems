package org.example;

import org.example.vectorCreating.*;

import java.util.ArrayList;

import static org.example.constants.Constants.COUNTRIES;
import static org.example.constants.Constants.EUCLIDEAN;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
//        main.convertsTextsIntoVectorsFiles();
        main.knnOperation();
//        main.countCountries(JsonConverter.loadDataFromJson("a.json"));


    }

    private void countCountries(ArrayList<DataObject> loadedData){

        System.out.println("total number of documents: " + loadedData.size());
        for(String country: COUNTRIES){
            int result = 0;
            for( DataObject entry: loadedData){
                for(String place: entry.getPlaces()){
                    if(place.equals(country)){
                        result++;
                    }
                }
            }
            System.out.println("country: " + country + ": " + result);
        }
    }

    private void convertsTextsIntoVectorsFiles() {
        DictionariesCreator dictionaries = new DictionariesCreator();
        SgmConverter converter = new SgmConverter();

//        converter.fillArticleList("reut2-014");
        converter.getArticlesFromData();
        ArrayList<Article> articlesInUse = converter.getArticleList();

        for (Article article : articlesInUse) {
            VectorCreator vectorCreator = new VectorCreator();
            vectorCreator.createNeighborVector(dictionaries.getPoliticians(), dictionaries.getCurrencies(), dictionaries.getGeography(), article.getWordsList());
            JsonConverter.appendDataToJson("b.json", vectorCreator.getVector(), vectorCreator.getWordsVector(), article.getPlaces());
        }
    }

    private void singleKnn(){
        KNN knn = new KNN();

        DataPreparer dp = new DataPreparer(JsonConverter.loadDataFromJson("a.json"), 30);
        ArrayList<DataObject> testLearningVectors = dp.getLearningData();
        ArrayList<DataObject> testTestVectors = dp.getTestData();

        QualityCalculator qualityCalculator = new QualityCalculator(knn.knn(testLearningVectors, testTestVectors, 12, EUCLIDEAN));
        qualityCalculator.showQualities();
    }

    private void knnOperation() {
        KNN knn = new KNN();

        DataPreparer dp = new DataPreparer(JsonConverter.loadDataFromJson("a.json"), 30);
        ArrayList<DataObject> testLearningVectors = dp.getLearningData();
        ArrayList<DataObject> testTestVectors = dp.getTestData();

//        QualityCalculator qualityCalculator = new QualityCalculator(knn.knn(testLearningVectors, testTestVectors, 12, EUCLIDEAN));
//        qualityCalculator.showQualities();

        for (int i = 2; i< 4; i++){
            System.out.println();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("            " + i + "           ");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println();
            for (int k = 2; k<21; k++){
                System.out.println("==== k = " + k);
                QualityCalculator qualityCalculator = new QualityCalculator(knn.knn(testLearningVectors, testTestVectors, k, i));
                qualityCalculator.showQualities();
    //            qualityCalculator.showComprehensiveQualities();
            }
        }

    }
}