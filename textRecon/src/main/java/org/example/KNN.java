package org.example;

import java.util.*;

public class KNN {

    Calculator calculator = new Calculator();

//    we need it to pass method to knn
    public interface MetricsFunction {
        double calculateDistance(ArrayList<Double> v1, ArrayList<Double> v2, ArrayList<Double> wordComp);
    }

    public Map<DataObject, String> knn(List<DataObject> learningData, List<DataObject> testData, int k, MetricsFunction metricsFunction) {
        Map<DataObject, String> results = new HashMap<>();

        for (DataObject td : testData) {
            Map<String, Double> neighbors = new HashMap<>();
            for (DataObject ld : learningData) {
                Double distance = metricsFunction.calculateDistance(td.getVector(), ld.getVector(), calculator.createWordComp(td.getWordVector(), ld.getWordVector()));
                for (String s : ld.getPlaces()) {
                    neighbors.put(s, distance);
                }
            }

            // sorting neighbour by distance
            List<Map.Entry<String, Double>> sortedNeighbors = new ArrayList<>(neighbors.entrySet());
            sortedNeighbors.sort(Map.Entry.comparingByValue());

            // choosing k nearest neighbours
            Map<String, Double> kNearestNeighbors = new LinkedHashMap<>();
            for (int i = 0; i < Math.min(k, sortedNeighbors.size()); i++) {
                Map.Entry<String, Double> entry = sortedNeighbors.get(i);
                kNearestNeighbors.put(entry.getKey(), entry.getValue());
            }

            // counting countries among k nearest
            Map<String, Integer> countryCounts = new HashMap<>();
            for (Map.Entry<String, Double> entry : kNearestNeighbors.entrySet()) {
                String country = entry.getKey();
                countryCounts.put(country, countryCounts.getOrDefault(country, 0) + 1);
            }

            // choosing most popular country
            String mostFrequentCountry = "";
            int maxCount = 0;
            for (Map.Entry<String, Integer> entry : countryCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    mostFrequentCountry = entry.getKey();
                    maxCount = entry.getValue();
                }
            }

            // creating map <dataObject, string> where string ist most popular country
            results.put(td, mostFrequentCountry);
        }

        return results;
    }


}
