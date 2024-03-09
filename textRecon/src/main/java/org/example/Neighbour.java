package org.example;

import java.util.*;

import static java.lang.Character.isUpperCase;

public class Neighbour {

    String country;
    List<String> vector;
    Map<String,String> politiciansDictionary;
    Map<String,String> currenciesDictionary;
    Map<String,String> keyWordsDictionary;

    /*

    standard component counting mechanic:
    [USA,CANADA,UK,JAPAN,FRANCE,WEST-GERMANY]

    #1 politiciansComponent
    #2 currencyComponent
    #3 keyWordComponent
    #4 number of capital letters words component
    #5 number of non capital letters words component
    #6 word number component
    #7 number of words longer than 10 letters or equal 10 letters
    #8 number of words shorter than 5 letters

    first 3 are counted as x = (highestCountryScore / totalScore)
    */

    public void createNeighbourVector(List<String> wordsList){

        int wordCounter = wordsList.size(); //6
        int longWordCounter = 0;    //7
        int shortWordCounter = 0;   //8
        int capitalWordCounter = 0; //4
        int nonCapitalWordCounter = 0; //5

        int totalPoliticians = 0;
        int totalCurrencies = 0;
        int totalKeyWords = 0;

//        cc == country counter
        Map<String, Integer> politiciansCC = createFilledMap();
        Map<String, Integer> currenciesCC = createFilledMap();
        Map<String, Integer> keyWordsCC = createFilledMap();

        for (String word: wordsList){

            if (word == null){
                continue;
            } else if (word == "") {
                continue;
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
        }
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
