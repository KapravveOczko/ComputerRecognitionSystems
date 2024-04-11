package org.example;

import java.util.*;

import static org.example.constants.Constants.*;

public class KNN {

    Calculator calculator = new Calculator();

    public static class Neighbor {
        private ArrayList<String> places;
        private Double distance;

        public Neighbor(ArrayList<String> places, Double distance) {
            this.places = places;
            this.distance = distance;
        }

        public ArrayList<String> getPlaces() {
            return places;
        }
        public Double getDistance() {
            return distance;
        }
    }

    public Map<DataObject, String> knn(List<DataObject> learningData, List<DataObject> testData, int k, int method) {
        Map<DataObject, String> results = new HashMap<>();
        Double distance = null;

        for (DataObject td : testData) {
            ArrayList<Neighbor> neighbors = new ArrayList<>();
            for (DataObject ld : learningData) {
                List<Integer> vectorIndexes = Arrays.asList(0,1,2,3,4);
                List<Integer> vectorWordIndexes = Arrays.asList(0,1,2);
                ArrayList<Double> tdVector = getSpecificVectorList(td, vectorIndexes);
                ArrayList<Double> ldVector = getSpecificVectorList(ld, vectorIndexes);
                ArrayList<String> tdWordVector = getSpecificWordVectorList(td, vectorWordIndexes);
                ArrayList<String> ldWordVector = getSpecificWordVectorList(ld, vectorWordIndexes);
                if(method == EUCLIDEAN) {
                    distance = this.calculator.euclideanMetrics(tdVector, ldVector, calculator.createWordComp(tdWordVector, ldWordVector));
                } else if (method == STREET){
                    distance = this.calculator.streetMetrics(tdVector, ldVector, calculator.createWordComp(tdWordVector, ldWordVector));
                } else if (method == CZEBYSZEW) {
                    distance = this.calculator.czebyszewMetrics(tdVector, ldVector, calculator.createWordComp(tdWordVector, ldWordVector));
                }
                neighbors.add(new Neighbor(ld.getPlaces(), distance));
            }

            // sorting neighbors
            Collections.sort(neighbors, Comparator.comparing(Neighbor::getDistance));
            List<Neighbor> kNearestNeighbors = neighbors.subList(0, Math.min(k, neighbors.size()));

            results.put(td,getResult(kNearestNeighbors));
        }
        return results;
    }


    public String getResult(List<Neighbor> kNearestNeighbors) {
        Map<String, Integer> countryScore = new HashMap<>();
        for (String country : COUNTRIES) {
            countryScore.put(country, 0);
        }

        for (Neighbor neighbor : kNearestNeighbors) {
            for (String place : neighbor.getPlaces()) {
                countryScore.put(place, countryScore.getOrDefault(place, 0) + 1);
            }
        }

        String maxCountry = "";
        int maxScore = 0;
        for (Map.Entry<String, Integer> entry : countryScore.entrySet()) {
            if (entry.getValue() > maxScore) {
                maxCountry = entry.getKey();
                maxScore = entry.getValue();
            }
        }

        // showCountCountries(countryScore);

        return maxCountry;
    }

    public void showResults(Map<DataObject, String> result){
        for (Map.Entry<DataObject, String> entry : result.entrySet()){
            System.out.println("powinno być:");
            System.out.println(entry.getKey().getPlaces());
            System.out.println("a jest:");
            System.out.println(entry.getValue());
        }
    }

    public void showCountCountries(Map<String, Integer> countryCounts){
        System.out.println("wyniki dla poszczególnych krajów:");
        for(Map.Entry<String, Integer> entry: countryCounts.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private ArrayList<Double> getSpecificVectorList(DataObject dataObject, List<Integer> indexes){
        ArrayList<Double> vector = new ArrayList<>();
        for (int index: indexes){
            vector.add(dataObject.getVector().get(index));
        }
        return vector;
    }

    private ArrayList<String> getSpecificWordVectorList(DataObject dataObject, List<Integer> indexes){
        ArrayList<String> vector = new ArrayList<>();
        for (int index: indexes){
            vector.add(dataObject.getWordVector().get(index));
        }
        return vector;
    }

}
