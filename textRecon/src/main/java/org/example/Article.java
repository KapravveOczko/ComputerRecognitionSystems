package org.example;

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
}
