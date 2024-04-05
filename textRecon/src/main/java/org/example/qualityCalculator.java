package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
list
tp <- 0
tn <- 1
fp <- 2
fn <- 3
*/

public class qualityCalculator {

    Map<String,Double> japanQuality;
    List<Integer> japanValues;
//===================================================
    Map<String,Double> usaQuality;
    List<Integer> usaValues;
//===================================================
    Map<String,Double> ukQuality;
    List<Integer> ukValues;
//===================================================
    Map<String,Double> franceQuality;
    List<Integer> franceValues;
//===================================================
    Map<String,Double> westGermanyQuality;
    List<Integer> westGermanyValues;
//===================================================
    Map<String,Double> canadaQuality;
    List<Integer> canadaValues;
//===================================================
    double accuracy;
    Map<String,Double> qualityC;


    public qualityCalculator(Map<DataObject, String> results) {

        double precisionSum = 0.0;
        double recallSum = 0.0;
        int tpSum = 0;

        this.usaValues = fillList();
        this.japanValues = fillList();
        this.ukValues = fillList();
        this.franceValues = fillList();
        this.canadaValues = fillList();
        this.westGermanyValues = fillList();

        qualityCheckByCountry(results);

        // usa
//        this.usaValues = qualityCheckByCountry(results, "usa");
        this.usaQuality = setQualityForCountry(usaValues.get(0), usaValues.get(1), usaValues.get(2), usaValues.get(3));
        precisionSum += usaQuality.get("precision") * usaValues.get(0);
        recallSum += usaQuality.get("recall") * usaValues.get(0);
        // japan
//        this.japanValues = qualityCheckByCountry(results, "japan");
        this.japanQuality = setQualityForCountry(japanValues.get(0), japanValues.get(1), japanValues.get(2), japanValues.get(3));
        precisionSum += japanQuality.get("precision") * japanValues.get(0);
        recallSum += japanQuality.get("recall") * japanValues.get(0);
        // uk
//        this.ukValues = qualityCheckByCountry(results, "uk");
        this.ukQuality = setQualityForCountry(ukValues.get(0), ukValues.get(1), ukValues.get(2), ukValues.get(3));
        precisionSum += ukQuality.get("precision") * ukValues.get(0);
        recallSum += ukQuality.get("recall") * ukValues.get(0);
        // france
//        this.franceValues = qualityCheckByCountry(results, "france");
        this.franceQuality = setQualityForCountry(franceValues.get(0), franceValues.get(1), franceValues.get(2), franceValues.get(3));
        precisionSum += franceQuality.get("precision") * franceValues.get(0);
        recallSum += franceQuality.get("recall") * franceValues.get(0);
        // canada
//        this.canadaValues = qualityCheckByCountry(results, "canada");
        this.canadaQuality = setQualityForCountry(canadaValues.get(0), canadaValues.get(1), canadaValues.get(2), canadaValues.get(3));
        precisionSum += canadaQuality.get("precision") * canadaValues.get(0);
        recallSum += canadaQuality.get("recall") * canadaValues.get(0);
        // west-germany
//        this.westGermanyValues = qualityCheckByCountry(results, "west-germany");
        this.westGermanyQuality = setQualityForCountry(westGermanyValues.get(0), westGermanyValues.get(1), westGermanyValues.get(2), westGermanyValues.get(3));
        precisionSum += westGermanyQuality.get("precision") * westGermanyValues.get(0);
        recallSum += westGermanyQuality.get("recall") * westGermanyValues.get(0);

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

            System.out.println();
            System.out.println();
            System.out.println("=================================");
            System.out.println("znaleziony kraj: " + predictedCountry);
            System.out.println("prawdziwy krej/kraje:");
            entry.getKey().showPlaces();
            System.out.println();
            System.out.println();

            if(predictedCountry.equals("usa")){
                if(actualCountries.contains("usa")) {
                    this.usaValues.set(0, this.usaValues.get(0) + 1);
                    this.ukValues.set(1, this.ukValues.get(1) + 1);
                    this.franceValues.set(1, this.franceValues.get(1) + 1);
                    this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                    this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    this.japanValues.set(1, this.japanValues.get(1) + 1);
                }
                else {
                    this.usaValues.set(2, this.usaValues.get(2) + 1);
                    if(actualCountries.contains("uk")){
                        this.ukValues.set(3, this.ukValues.get(3) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    }else if(actualCountries.contains("japan")){
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                        this.japanValues.set(3, this.japanValues.get(3) + 1);
                    }else if(actualCountries.contains("west-germany")){
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.westGermanyValues.set(3, this.westGermanyValues.get(3) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    } else if(actualCountries.contains("canada")){
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.canadaValues.set(3, this.canadaValues.get(3) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    } else if(actualCountries.contains("france")){
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.franceValues.set(3, this.franceValues.get(3) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    }
                }
            }

            if(predictedCountry.equals("uk")){
                if(actualCountries.contains("uk")) {
                    this.usaValues.set(1, this.usaValues.get(1) + 1);
                    this.ukValues.set(0, this.ukValues.get(0) + 1);
                    this.franceValues.set(1, this.franceValues.get(1) + 1);
                    this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                    this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    this.japanValues.set(1, this.japanValues.get(1) + 1);
                }
                else {
                    this.ukValues.set(2, this.ukValues.get(2) + 1);
                    if(actualCountries.contains("usa")){
                        this.usaValues.set(3, this.usaValues.get(3) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    }else if(actualCountries.contains("japan")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                        this.japanValues.set(3, this.japanValues.get(3) + 1);
                    }else if(actualCountries.contains("west-germany")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.westGermanyValues.set(3, this.westGermanyValues.get(3) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    } else if(actualCountries.contains("canada")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.canadaValues.set(3, this.canadaValues.get(3) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    } else if(actualCountries.contains("france")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.franceValues.set(3, this.franceValues.get(3) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    }
                }
            }


            if(predictedCountry.equals("france")){
                if(actualCountries.contains("france")) {
                    this.usaValues.set(1, this.usaValues.get(1) + 1);
                    this.ukValues.set(1, this.ukValues.get(1) + 1);
                    this.franceValues.set(0, this.franceValues.get(0) + 1);
                    this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                    this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    this.japanValues.set(1, this.japanValues.get(1) + 1);
                }
                else {
                    this.franceValues.set(2, this.franceValues.get(2) + 1);
                    if(actualCountries.contains("usa")){
                        this.usaValues.set(3, this.usaValues.get(3) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    }else if(actualCountries.contains("japan")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                        this.japanValues.set(3, this.japanValues.get(3) + 1);
                    }else if(actualCountries.contains("west-germany")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.westGermanyValues.set(3, this.westGermanyValues.get(3) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    } else if(actualCountries.contains("canada")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.canadaValues.set(3, this.canadaValues.get(3) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    } else if(actualCountries.contains("uk")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(3, this.ukValues.get(3) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    }
                }
            }


            if(predictedCountry.equals("west-germany")){
                if(actualCountries.contains("west-germany")) {
                    this.usaValues.set(1, this.usaValues.get(1) + 1);
                    this.ukValues.set(1, this.ukValues.get(1) + 1);
                    this.franceValues.set(1, this.franceValues.get(1) + 1);
                    this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                    this.westGermanyValues.set(0, this.westGermanyValues.get(0) + 1);
                    this.japanValues.set(1, this.japanValues.get(1) + 1);
                }
                else {
                    this.westGermanyValues.set(2, this.ukValues.get(2) + 1);
                    if(actualCountries.contains("usa")){
                        this.usaValues.set(3, this.usaValues.get(3) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    }else if(actualCountries.contains("japan")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.japanValues.set(3, this.japanValues.get(3) + 1);
                    }else if(actualCountries.contains("france")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.franceValues.set(3, this.franceValues.get(3) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    } else if(actualCountries.contains("canada")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.canadaValues.set(3, this.canadaValues.get(3) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    } else if(actualCountries.contains("uk")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(3, this.ukValues.get(3) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                    }
                }
            }


            if(predictedCountry.equals("japan")){
                if(actualCountries.contains("japan")) {
                    this.usaValues.set(1, this.usaValues.get(1) + 1);
                    this.ukValues.set(1, this.ukValues.get(1) + 1);
                    this.franceValues.set(1, this.franceValues.get(1) + 1);
                    this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                    this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    this.japanValues.set(0, this.japanValues.get(0) + 1);
                }
                else {
                    this.japanValues.set(2, this.japanValues.get(2) + 1);
                    if(actualCountries.contains("usa")){
                        this.usaValues.set(3, this.usaValues.get(3) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    }else if(actualCountries.contains("west-germany")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.westGermanyValues.set(3, this.westGermanyValues.get(3) + 1);
                    }else if(actualCountries.contains("france")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.franceValues.set(3, this.franceValues.get(3) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    } else if(actualCountries.contains("canada")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.canadaValues.set(3, this.canadaValues.get(3) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    } else if(actualCountries.contains("uk")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(3, this.ukValues.get(3) + 1);
                        this.canadaValues.set(1, this.canadaValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    }
                }
            }


            if(predictedCountry.equals("canada")){
                if(actualCountries.contains("canada")) {
                    this.usaValues.set(1, this.usaValues.get(1) + 1);
                    this.ukValues.set(1, this.ukValues.get(1) + 1);
                    this.franceValues.set(1, this.franceValues.get(1) + 1);
                    this.canadaValues.set(0, this.canadaValues.get(0) + 1);
                    this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    this.japanValues.set(1, this.japanValues.get(1) + 1);
                }
                else {
                    this.canadaValues.set(2, this.canadaValues.get(2) + 1);
                    if(actualCountries.contains("usa")){
                        this.usaValues.set(3, this.usaValues.get(3) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    }else if(actualCountries.contains("west-germany")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.westGermanyValues.set(3, this.westGermanyValues.get(3) + 1);
                    }else if(actualCountries.contains("france")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                        this.franceValues.set(3, this.franceValues.get(3) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    } else if(actualCountries.contains("japan")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(1, this.ukValues.get(1) + 1);
                        this.japanValues.set(3, this.japanValues.get(3) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    } else if(actualCountries.contains("uk")){
                        this.usaValues.set(1, this.usaValues.get(1) + 1);
                        this.ukValues.set(3, this.ukValues.get(3) + 1);
                        this.japanValues.set(1, this.japanValues.get(1) + 1);
                        this.franceValues.set(1, this.franceValues.get(1) + 1);
                        this.westGermanyValues.set(1, this.westGermanyValues.get(1) + 1);
                    }
                }
            }

        }
    }

/*    public List<Integer> qualityCheckByCountry(Map<DataObject, String> results, String country) {

        int tp = 0, fp = 0, tn = 0, fn = 0;
        List<Integer> resultList = new ArrayList<>();

        for (Map.Entry<DataObject, String> entry : results.entrySet()) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("znaleziony kraj: " + country);
            System.out.println("prawdziwy krej/kraje:");
            entry.getKey().showPlaces();
            System.out.println();



            String predictedCountry = entry.getValue();
            List<String> actualCountries = entry.getKey().getPlaces();

            if (actualCountries.contains(country)) {
                if (predictedCountry.equals(country)) {
                    tp++;
                    System.out.println("dodane do tp");
                } else {
                    fn++;
                    System.out.println("dodane do fn");
                }
            } else {
                if (predictedCountry.equals(country)) {
                    fp++;
                    System.out.println("dodane do fp");
                } else {
                    tn++;
                    System.out.println("dodane do tn");
                }
            }
        }

        resultList.add(tp);
        resultList.add(tn);
        resultList.add(fp);
        resultList.add(fn);
        return resultList;
    }*/


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
        System.out.println("precision: " + this.westGermanyQuality.get("precision"));
        System.out.println("recall: " + this.westGermanyQuality.get("recall"));
        System.out.println("f1: " + this.westGermanyQuality.get("f1"));
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
