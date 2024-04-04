package org.example;

import java.util.*;

import static java.lang.Double.NaN;

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
                Double distance = this.calculator.euclideanMetrics(td.getVector(), ld.getVector(), calculator.createWordComp(td.getWordVector(), ld.getWordVector()));
//                System.out.println(distance);
                neighbours.add(new Neighbour(ld.getPlaces(), distance));
            }

            // sorting neighbours
            Collections.sort(neighbours, Comparator.comparing(Neighbour::getDistance));
            List<Neighbour> kNearestNeighbours = neighbours.subList(0, Math.min((k - 1), neighbours.size()));

            // checking neighbours
/*            for (Neighbour neighbour : kNearestNeighbours) {
                System.out.println("country distance " + neighbour.getPlaces() + " | " + neighbour.getDistance());
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("wynik " + getResult(kNearestNeighbours));
*/

            results.put(td,getResult(kNearestNeighbours));
        }
        return results;
    }


    public String getResult(List<Neighbour> kNearestNeighbors) {
        Map<String, Integer> countryScore = new HashMap<>();
        countryScore.put("canada", 0);
        countryScore.put("japan", 0);
        countryScore.put("west-germany", 0);
        countryScore.put("uk", 0);
        countryScore.put("france", 0);
        countryScore.put("usa", 0);

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

        showCountCountries(countryScore);

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

}
