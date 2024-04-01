package org.example;

import java.util.*;

import static java.lang.Character.isUpperCase;

public class Neighbour {

    String country;
    double[] vector;

    /*

    standard component counting mechanic:
    [USA,CANADA,UK,JAPAN,FRANCE,WEST-GERMANY]

    #1 politiciansComponent
    #2 currencyComponent
    #3 keyWordComponent
    #4 geographyComponent
    #5 number of capital letters words component
    #6 number of non capital letters words component
    #7 word number component
    #8 number of words longer than 10 letters or equal 10 letters
    #9 number of words shorter than 5 letters
    #10 the longest word

    first 3 are counted as x = (highestCountryScore / totalScore)
    */

    public void createNeighbourVector( Map<String,String> politiciansDictionary,
                                       Map<String,String> currenciesDictionary ,
                                       Map<String,String> keyWordsDictionary,
                                       Map<String,String> geographyDictionary,
                                       List<String> wordsList){

        int wordCounter = wordsList.size(); //7
        int longWordCounter = 0;    //8
        int shortWordCounter = 0;   //9
        int capitalWordCounter = 0; //5
        int nonCapitalWordCounter = 0; //6
        int longestWord = 0; //10

        int totalPoliticians = 0;   //1
        int totalCurrencies = 0;    //2
        int totalKeyWords = 0;  //3
        int totalGeography = 0; //4

//        cc == country counter
        Map<String, Integer> politiciansCC = createFilledMap();
        Map<String, Integer> currenciesCC = createFilledMap();
        Map<String, Integer> keyWordsCC = createFilledMap();
        Map<String, Integer> geographyCC = createFilledMap();

        for (String word: wordsList){

            if (word == null){
                continue;
            } else if (word == "") {
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

//            TO DO:
//            compare words witch keys in lowercase

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
            if(keyWordsDictionary.containsKey(word)){
                totalKeyWords++;
                country = keyWordsDictionary.get(word);
                keyWordsCC.replace(country, keyWordsCC.get(country) + 1);
            }
            if(geographyDictionary.containsKey(word)){
                totalGeography++;
                country = geographyDictionary.get(word);
                geographyCC.replace(country, geographyCC.get(country) + 1);
            }
        }

        double[] vector = new double[10];
        vector[0] = (double) getHighestCCValue(politiciansCC) / totalPoliticians;
        vector[1] = (double) getHighestCCValue(currenciesCC) / totalCurrencies;
        vector[2] = (double) getHighestCCValue(keyWordsCC) / totalKeyWords;
        vector[3] = (double) getHighestCCValue(geographyCC) / totalGeography;
        vector[4] = capitalWordCounter;
        vector[5] = nonCapitalWordCounter;
        vector[6] = wordCounter;
        vector[7] = longWordCounter;
        vector[8] = shortWordCounter;
        vector[9] = longestWord;

        this.vector = vector;
    }

    public int getHighestCCValue(Map<String, Integer> ccMap){

        int highest = 0;
        Set<String> ccCountry = ccMap.keySet();
        for(String cc: ccCountry){
            if(ccMap.get(cc) > highest){
                highest = ccMap.get(cc);
            }
        }

        return highest;
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


}
