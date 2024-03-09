package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Article {

    List<String> places;
    String content;

    public Article(List<String> places, String content) {
        this.places = places;
        this.content = content;
    }

    public List<String> getPlaces() {
        return places;
    }

    public String getContent() {
        return content;
    }

    public List<String> getWordsList() {
        String[] words = getContent().split(" ");
        return Arrays.asList(words);
    }

    public List<String> cleanUpWordsList(List<String> wordsList){
        List<String> cleanedList = new ArrayList<>();
        for(String word: wordsList){
            if (word.endsWith(",") || word.endsWith(".")){
                cleanedList.add(word.substring(0,word.length()-1));
            }
            else {
                cleanedList.add(word);
            }
        }
        return cleanedList;
    }

}
