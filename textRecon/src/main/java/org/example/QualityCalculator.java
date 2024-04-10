package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.constants.Constants.*;

public class QualityCalculator {

    double accuracy;
    Map<String,Double> qualityC;

    Map<String, Map<String, Double>> allQualities;
    Map<String, List<Integer>> allValues;


    public QualityCalculator(Map<DataObject, String> results) {

        double precisionSum = 0.0;
        double recallSum = 0.0;
        double tpSum = 0.0;
        double allPopulation = 0.0;

        this.allQualities = new HashMap<>();
        this.allValues = new HashMap<>();

        for (String country : COUNTRIES) {
            this.allValues.put(country, fillList());
        }

        qualityCheckByCountry(results);

        for (String country : COUNTRIES) {
            this.allQualities.put(country, setQualityForCountry(allValues.get(country).get(TP), allValues.get(country).get(TN), allValues.get(country).get(FP), allValues.get(country).get(FN)));
            precisionSum += allQualities.get(country).get("precision") * allValues.get(country).get(TP);
            recallSum += allQualities.get(country).get("recall") * allValues.get(country).get(TP);
            tpSum += allValues.get(country).get(TP);
            allPopulation += allValues.get(country).get(TP) + allValues.get(country).get(FP);
        }

        this.accuracy = tpSum / allPopulation;
        this.qualityC = setUniversalQuality(tpSum, recallSum, precisionSum);

//        for (String country : COUNTRIES) {
//            showValuesByCountry(country, allValues.get(country));
//        }

    }

    public List<Integer> fillList(){
        List<Integer> result = new ArrayList<>();
        result.add(0);
        result.add(0);
        result.add(0);
        result.add(0);
        return result;
    }

    public void qualityCheckByCountry(Map<DataObject, String> results) {

        for (Map.Entry<DataObject, String> entry : results.entrySet()) {

            String predictedCountry = entry.getValue();
            List<String> actualCountries = entry.getKey().getPlaces();

            // entry.getKey().showPlaces();

            if (actualCountries.contains(predictedCountry)) {
                allValues.get(predictedCountry).set(TP, allValues.get(predictedCountry).get(TP) + 1);
                for (String country : COUNTRIES) {
                    if (!country.equals(predictedCountry)) {
                        allValues.get(country).set(TN, allValues.get(country).get(TN) + 1);
                    }
                }
            } else {
                allValues.get(predictedCountry).set(FP, allValues.get(predictedCountry).get(FP) + 1);
                for (String country : COUNTRIES) {
                    if (!country.equals(predictedCountry)) {
                        if (actualCountries.contains(country)) {
                            allValues.get(country).set(FN, allValues.get(country).get(FN) + 1);
                        } else {
                            allValues.get(country).set(TN, allValues.get(country).get(TN) + 1);
                        }
                    }
                }
            }

        }
    }

    public double accuracy(int tp, int tn, int pop){
        return (double) (tp + tn) / pop;
    }

    public double precision(int tp, int fp){
        if(tp + fp == 0.0){
            return 0.0;
        }
        return (double) tp/(tp + fp);
    }
    public double recall(int tp, int fn){
        return (double) tp/(tp+fn);
    }
    public double f1(double precision, double recall){
        if (precision + recall == 0){
            return 0.0;
        }
        return (double) 2 * ((precision * recall)/(precision + recall));
    }

    public double precisionC(double tpSum, double precisionSum){
        return precisionSum/tpSum;
    }
    public double recallC(double tpSum, double recallSum){
        return  recallSum/tpSum;
    }
    public double f1C( double recallC, double precisionC){
        return 2*((recallC * precisionC)/(recallC + precisionC));
    }

    public Map<String,Double> setQualityForCountry(int tp, int tn, int fp, int fn){
        Map<String,Double> results = new HashMap<>();

        results.put("precision", precision(tp, fp));
        results.put("recall", recall(tp, fn));
        results.put("f1", f1(results.get("precision"), results.get("recall")));

        return results;
    }

    public Map<String,Double> setUniversalQuality(double tpSum, double recallSum, double precisionSum){
        Map<String,Double> results = new HashMap<>();

        results.put("precisionC", precisionC(tpSum, precisionSum));
        results.put("recallC", recallC(tpSum, recallSum));
        results.put("f1C", f1C(results.get("recallC"), results.get("precisionC")));

        return results;
    }

    public void showQualities(){
        System.out.println("Wartości miar jakości klasyfikacji:");
        System.out.println();
        System.out.println("Wartośći ogólne:");
        System.out.println("accuracy: " + this.accuracy);
        System.out.println("precisionC: " + this.qualityC.get("precisionC"));
        System.out.println("recallC: " + this.qualityC.get("recallC"));
        System.out.println("f1C: " + this.qualityC.get("f1C"));
        System.out.println();
        for (String country : COUNTRIES) {
            System.out.println("Wartości dla " + country + ":");
            System.out.println("precision: " + this.allQualities.get(country).get("precision"));
            System.out.println("recall: " + this.allQualities.get(country).get("recall"));
            System.out.println("f1: " + this.allQualities.get(country).get("f1"));
            System.out.println();
        }
    }

    public void showComprehensiveQualities(){
        System.out.println("Wartośći ogólne:");
        System.out.println("accuracy: " + this.accuracy);
        System.out.println("precisionC: " + this.qualityC.get("precisionC"));
        System.out.println("recallC: " + this.qualityC.get("recallC"));
        System.out.println("f1C: " + this.qualityC.get("f1C"));
    }

    public void showAccuracyQualities(){
        for (String country : COUNTRIES) {
            System.out.println("Wartości dla " + country + ":");
            System.out.println("precision: " + this.allQualities.get(country).get("precision"));
            System.out.println("recall: " + this.allQualities.get(country).get("recall"));
            System.out.println("f1: " + this.allQualities.get(country).get("f1"));
            System.out.println();
        }
    }

    public void showValuesByCountry(String country, List<Integer> values){
        System.out.println("values for " + country);
        System.out.println("tp: " + values.get(TP));
        System.out.println("tn: " + values.get(TN));
        System.out.println("fp: " + values.get(FP));
        System.out.println("fn: " + values.get(FN));
    }

}
