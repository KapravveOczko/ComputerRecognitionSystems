package org.example;

import java.util.*;

import static org.example.constants.Constants.*;

public class KNN {

    Calculator calculator = new Calculator();

    public static class Neighbour {
        private ArrayList<String> places;
        private Double distance;

        public Neighbour(ArrayList<String> places, Double distance) {
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

    public Map<DataObject, String> knn(List<DataObject> learningData, List<DataObject> testData, int k) {
        Map<DataObject, String> results = new HashMap<>();

        for (DataObject td : testData) {
            ArrayList<Neighbour> neighbours = new ArrayList<>();
            for (DataObject ld : learningData) {
                List<Integer> vectorIndexes = Arrays.asList(0,1,2,3,4,5,6);
                List<Integer> vectorWordIndexes = Arrays.asList(0,1,2);
                ArrayList<Double> tdVector = getSpecificVectorList(td, vectorIndexes);
                ArrayList<Double> ldVector = getSpecificVectorList(ld, vectorIndexes);
                ArrayList<String> tdWordVector = getSpecificWordVectorList(td, vectorWordIndexes);
                ArrayList<String> ldWordVector = getSpecificWordVectorList(ld, vectorWordIndexes);
                Double distance = this.calculator.czebyszewMetrics(tdVector, ldVector, calculator.createWordComp(tdWordVector, ldWordVector));
                neighbours.add(new Neighbour(ld.getPlaces(), distance));
            }

            // sorting neighbours
            Collections.sort(neighbours, Comparator.comparing(Neighbour::getDistance));
            List<Neighbour> kNearestNeighbours = neighbours.subList(0, Math.min(k, neighbours.size()));

            results.put(td,getResult(kNearestNeighbours));
        }
        return results;
    }


    public String getResult(List<Neighbour> kNearestNeighbors) {
        Map<String, Integer> countryScore = new HashMap<>();
        for (String country : COUNTRIES) {
            countryScore.put(country, 0);
        }

        for (Neighbour neighbour : kNearestNeighbors) {
            for (String place : neighbour.getPlaces()) {
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
