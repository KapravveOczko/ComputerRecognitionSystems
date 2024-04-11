package org.example;

import org.example.vectorCreating.*;

import java.util.ArrayList;

import static org.example.constants.Constants.*;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
//        main.convertsTextsIntoVectorsFiles();
//        main.knnOperation(1,20,30);
//        main.singleKnn(12, STREET, 10);
//        main.countCountries(JsonConverter.loadDataFromJson("a.json"));


        System.out.println("===============================================");
        System.out.println("            " + 10 + " %         ");
        System.out.println("             IV");
        System.out.println("===============================================");
        System.out.println("EUKLIDES");
        main.singleKnn(17, EUCLIDEAN, 10);
        System.out.println("ULICZNA");
        main.singleKnn(12, STREET, 10);
        System.out.println("CZEBYSZEW");
        main.singleKnn(19, CZEBYSZEW, 10);



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

    private void singleKnn(int k, int metrics, int percentage){
        KNN knn = new KNN();

        DataPreparer dp = new DataPreparer(JsonConverter.loadDataFromJson("a.json"), percentage);
        ArrayList<DataObject> testLearningVectors = dp.getLearningData();
        ArrayList<DataObject> testTestVectors = dp.getTestData();

        QualityCalculator qualityCalculator = new QualityCalculator(knn.knn(testLearningVectors, testTestVectors, k, metrics));
        qualityCalculator.showQualities();
    }

    private void knnOperation(int kStart, int kEnd, int percentage) {
        KNN knn = new KNN();

        DataPreparer dp = new DataPreparer(JsonConverter.loadDataFromJson("a.json"), percentage);
        ArrayList<DataObject> testLearningVectors = dp.getLearningData();
        ArrayList<DataObject> testTestVectors = dp.getTestData();
        

        for (int i = 1; i< 4; i++){
            System.out.println();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("            " + i + "           ");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println();
            for (int k = kStart; k<kEnd; k++){
                System.out.println("==== k = " + k);
                QualityCalculator qualityCalculator = new QualityCalculator(knn.knn(testLearningVectors, testTestVectors, k, i));
                qualityCalculator.showQualities();
    //            qualityCalculator.showComprehensiveQualities();
            }
        }

    }
}