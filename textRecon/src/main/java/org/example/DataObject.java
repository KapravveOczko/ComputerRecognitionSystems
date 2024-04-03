package org.example;

import java.util.ArrayList;

public class DataObject {

    ArrayList<Double> vector;
    ArrayList<String> wordVector;
    ArrayList<String> places;

    public DataObject(ArrayList<Double> vector, ArrayList<String> wordVector, ArrayList<String> places) {
        this.vector = vector;
        this.wordVector = wordVector;
        this.places = places;
    }

    public ArrayList<Double> getVector() {
        return vector;
    }

    public ArrayList<String> getWordVector() {
        return wordVector;
    }

    public ArrayList<String> getPlaces() {
        return places;
    }
}
