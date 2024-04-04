package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class qualityCalculator {

    double accuracy;
    Map<String,Double> japanQuality;
    Map<String,Double> usaQuality;
    Map<String,Double> ukQuality;
    Map<String,Double> franceQuality;
    Map<String,Double> westgermanyQuality;
    Map<String,Double> canadaQuality;

    Map<String,Double> qualityC;


    public qualityCalculator(Map<DataObject, String> results) {

        double precisionSum = 0.0;
        double recallSum = 0.0;
        int tpSum = 0;

        // usa
        List<Integer> usaValues = qualityCheckByCountry(results, "usa");
        this.usaQuality = setQualityForCountry(usaValues.get(0), usaValues.get(1), usaValues.get(2), usaValues.get(3));
        precisionSum += usaQuality.get("precision") * usaValues.get(0);
        recallSum += usaQuality.get("recall") * usaValues.get(0);
        // japan
        List<Integer> japanValues = qualityCheckByCountry(results, "japan");
        this.japanQuality = setQualityForCountry(japanValues.get(0), japanValues.get(1), japanValues.get(2), japanValues.get(3));
        precisionSum += japanQuality.get("precision") * japanValues.get(0);
        recallSum += japanQuality.get("recall") * japanValues.get(0);
        // uk
        List<Integer> ukValues = qualityCheckByCountry(results, "uk");
        this.ukQuality = setQualityForCountry(ukValues.get(0), ukValues.get(1), ukValues.get(2), ukValues.get(3));
        precisionSum += ukQuality.get("precision") * ukValues.get(0);
        recallSum += ukQuality.get("recall") * ukValues.get(0);
        // france
        List<Integer> franceValues = qualityCheckByCountry(results, "france");
        this.franceQuality = setQualityForCountry(franceValues.get(0), franceValues.get(1), franceValues.get(2), franceValues.get(3));
        precisionSum += franceQuality.get("precision") * franceValues.get(0);
        recallSum += franceQuality.get("recall") * franceValues.get(0);
        // canada
        List<Integer> canadaValues = qualityCheckByCountry(results, "canada");
        this.canadaQuality = setQualityForCountry(canadaValues.get(0), canadaValues.get(1), canadaValues.get(2), canadaValues.get(3));
        precisionSum += canadaQuality.get("precision") * canadaValues.get(0);
        recallSum += canadaQuality.get("recall") * canadaValues.get(0);
        // west-germany
        List<Integer> westGermanyValues = qualityCheckByCountry(results, "west-germany");
        this.westgermanyQuality = setQualityForCountry(westGermanyValues.get(0), westGermanyValues.get(1), westGermanyValues.get(2), westGermanyValues.get(3));
        precisionSum += westgermanyQuality.get("precision") * westGermanyValues.get(0);
        recallSum += westgermanyQuality.get("recall") * westGermanyValues.get(0);

        tpSum = usaValues.get(0) + ukValues.get(0) + japanValues.get(0) + franceValues.get(0) + canadaValues.get(0) + westGermanyValues.get(0);
        this.qualityC = setUniversalQuality(tpSum, recallSum, precisionSum);
        this.accuracy = accuracy(usaValues.get(0), usaValues.get(1), usaValues.get(0) + usaValues.get(1) + usaValues.get(2) + usaValues.get(3));

        showValuesByCountry("usa", usaValues);
        showValuesByCountry("japan", japanValues);
        showValuesByCountry("uk", ukValues);
        showValuesByCountry("france", franceValues);
        showValuesByCountry("west-germany", westGermanyValues);
        showValuesByCountry("canada", canadaValues);

    }

    public List<Integer> qualityCheckByCountry(Map<DataObject, String> results, String country) {

        int tp = 0, fp = 0, tn = 0, fn = 0;
        List<Integer> resultList = new ArrayList<>();

        for (Map.Entry<DataObject, String> entry : results.entrySet()) {
            String predictedCountry = entry.getValue();
            List<String> actualCountries = entry.getKey().getPlaces();

            if (actualCountries.contains(country)) {
                if (predictedCountry.equals(country)) {
                    tp++;
                } else {
                    fn++;
                }
            } else {
                if (predictedCountry.equals(country)) {
                    fp++;
                } else {
                    tn++;
                }
            }
        }

        resultList.add(tp);
        resultList.add(tn);
        resultList.add(fp);
        resultList.add(fn);
        return resultList;
    }


    public double accuracy(int tp, int tn, int pop){
        return (double) (tp + tn) /pop;
    }
    public double precision(int tp, int fp){
        return (double) tp/(tp + fp);
    }
    public double recall(int tp, int fn){
        return (double) tp/(tp+fn);
    }
    public double f1(double precision, double recall){
        return 2 * ((precision * recall)/(recall + precision));
    }

    // for C
    public double precisionC(int tpSum, double precisionSum){
        return precisionSum/tpSum;
    }
    public double recallC(int tpSum, double recallSum){
        return  recallSum/tpSum;
    }
    public double f1C( double recallC, double precisionC){
        return 2*((recallC * precisionC)/(recallC + precisionC));
    }

    public Map<String,Double> setQualityForCountry(int tp, int tn, int fp, int fn){
        Map<String,Double> results = new HashMap<>();

//        results.put("accuracy", accuracy(tp, tn, tp + fp + tn + fn));
        results.put("precision", precision(tp, fp));
        results.put("recall", recall(tp, fn));
        results.put("f1", f1(results.get("precision"), results.get("recall")));

        return results;
    }

    public Map<String,Double> setUniversalQuality(int tpSum, double recallSum, double precisionSum){
        Map<String,Double> results = new HashMap<>();

        results.put("precisionC", precisionC(tpSum, precisionSum));
        results.put("recallC", recallC(tpSum, recallSum));
        results.put("f1C", f1(results.get("recallC"), results.get("precisionC")));

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
        System.out.println("Wartości dla usa:");
        System.out.println("precision: " + this.usaQuality.get("precision"));
        System.out.println("recall: " + this.usaQuality.get("recall"));
        System.out.println("f1: " + this.usaQuality.get("f1"));
        System.out.println();
        System.out.println("Wartości dla japan:");
        System.out.println("precision: " + this.japanQuality.get("precision"));
        System.out.println("recall: " + this.japanQuality.get("recall"));
        System.out.println("f1: " + this.japanQuality.get("f1"));
        System.out.println();
        System.out.println("Wartości dla uk:");
        System.out.println("precision: " + this.ukQuality.get("precision"));
        System.out.println("recall: " + this.ukQuality.get("recall"));
        System.out.println("f1: " + this.ukQuality.get("f1"));
        System.out.println();
        System.out.println("Wartości dla france:");
        System.out.println("precision: " + this.franceQuality.get("precision"));
        System.out.println("recall: " + this.franceQuality.get("recall"));
        System.out.println("f1: " + this.franceQuality.get("f1"));
        System.out.println();
        System.out.println("Wartości dla west-germany:");
        System.out.println("precision: " + this.westgermanyQuality.get("precision"));
        System.out.println("recall: " + this.westgermanyQuality.get("recall"));
        System.out.println("f1: " + this.westgermanyQuality.get("f1"));
        System.out.println();
        System.out.println("Wartości dla canada:");
        System.out.println("precision: " + this.canadaQuality.get("precision"));
        System.out.println("recall: " + this.canadaQuality.get("recall"));
        System.out.println("f1: " + this.canadaQuality.get("f1"));
    }

    public void showValuesByCountry(String country, List<Integer> values){
        System.out.println("values for " + country);
        System.out.println("tp: " + values.get(0));
        System.out.println("tn: " + values.get(1));
        System.out.println("fp: " + values.get(2));
        System.out.println("fn: " + values.get(3));
    }

}
