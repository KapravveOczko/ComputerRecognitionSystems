package org.example.vectorCreating;

import java.util.*;

import static java.lang.Character.isUpperCase;

public class VectorCreator {

    ArrayList<String> wordsVector;
    ArrayList<Double> vector;

    /*

    standard component counting mechanic:
    [USA,CANADA,UK,JAPAN,FRANCE,WEST-GERMANY]

    #1 politiciansComponent
    #2 currencyComponent
    #3 geographyComponent
    #4 number of capital letters words component
    #5 number of non capital letters words component
    #6 word number component
    #7 number of words longer than 10 letters or equal 10 letters
    #8 number of words shorter than 5 letters
    #9 the longest word
    #10 words containing "-"

    first 3 are counted as x = (highestCountryScore / totalScore)
    */

    public void createNeighborVector( Map<String,String> politiciansDictionary,
                                       Map<String,String> currenciesDictionary ,
                                       Map<String,String> geographyDictionary,
                                       List<String> wordsList){

        String country;

        int wordCounter = wordsList.size(); //6
        int longWordCounter = 0;    //7
        int shortWordCounter = 0;   //8
        int capitalWordCounter = 0; //4
        int nonCapitalWordCounter = 0; //5
        int longestWord = 0; //9
        int wordsWithHyphen = 0; //10

        int totalPoliticians = 0;   //1
        int totalCurrencies = 0;    //2
        int totalGeography = 0; //4

//        cc == country counter
        Map<String, Integer> politiciansCC = createFilledMap();
        Map<String, Integer> currenciesCC = createFilledMap();
        Map<String, Integer> geographyCC = createFilledMap();

        for (String word: wordsList){

            if (word == null){
                continue;
            } else if (word.isEmpty()) {
                continue;
            }

            if (word.length() > longestWord){
                longestWord = word.length();
            }

            if (word.length() >= 10){
                longWordCounter ++;
            } else if (word.length() <= 5) {
                shortWordCounter ++;
            }

            if (isUpperCase(word.charAt(0))){
                capitalWordCounter++;
            }
            else {
                nonCapitalWordCounter++;
            }
            if (word.contains("-")){
                wordsWithHyphen ++;
            }

            word = word.toLowerCase();

            if(politiciansDictionary.containsKey(word)){
                totalPoliticians++;
                country = politiciansDictionary.get(word);
                politiciansCC.replace(country, politiciansCC.get(country) + 1);
            }
            if(currenciesDictionary.containsKey(word)){
                totalCurrencies++;
                country = currenciesDictionary.get(word);
                currenciesCC.replace(country, currenciesCC.get(country) + 1);
            }
            if(geographyDictionary.containsKey(word)){
                totalGeography++;
                country = geographyDictionary.get(word);
                geographyCC.replace(country, geographyCC.get(country) + 1);
            }
        }

//        vector[0] = (double) getHighestCCValue(politiciansCC) / totalPoliticians;
//        vector[1] = (double) getHighestCCValue(currenciesCC) / totalCurrencies;
//        vector[3] = (double) getHighestCCValue(geographyCC) / totalGeography;

        ArrayList<Double> vector = new ArrayList<>();
        vector.add((double) capitalWordCounter);
        vector.add((double) nonCapitalWordCounter);
        vector.add((double) wordCounter);
        vector.add((double) longWordCounter);
        vector.add((double) shortWordCounter);
        vector.add((double) longestWord);
        vector.add((double) wordsWithHyphen);

        ArrayList<String> wordVector = new ArrayList<>();
        wordVector.add(getHighestCCValue(politiciansCC));
        wordVector.add(getHighestCCValue(currenciesCC));
        wordVector.add(getHighestCCValue(geographyCC));

        this.vector = vector;
        this.wordsVector = wordVector;
    }


//    public int getHighestCCValue(Map<String, Integer> ccMap){
//
//        int highest = 0;
//        Set<String> ccCountry = ccMap.keySet();
//        for(String cc: ccCountry){
//            if(ccMap.get(cc) > highest){
//                highest = ccMap.get(cc);
//            }
//        }
//
//        return highest;
//    }

    public String getHighestCCValue(Map<String, Integer> ccMap){

        int highest = 0;
        String country = "";
        Set<String> ccCountry = ccMap.keySet();
        for(String cc: ccCountry){
            if(ccMap.get(cc) > highest){
                highest = ccMap.get(cc);
                country = cc;
            }
        }

        return country;
    }

    public static Map<String, Integer> createFilledMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("usa", 0);
        map.put("canada", 0);
        map.put("uk", 0);
        map.put("japan", 0);
        map.put("france", 0);
        map.put("west-germany", 0);
        return map;
    }

    public void showVector(){
        System.out.println("Vector: ");
        for (String wv : this.wordsVector) {
            System.out.print(wv + ", ");
        }
        for (double v : this.vector) {
            System.out.print(v + ", ");
        }
        System.out.println();
    }

    public ArrayList<String> getWordsVector() {
        return wordsVector;
    }

    public ArrayList<Double> getVector() {
        return vector;
    }
}
