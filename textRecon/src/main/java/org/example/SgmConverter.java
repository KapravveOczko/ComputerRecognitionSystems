package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SgmConverter {

    private List<String> placesList;
    private  ArrayList<Article> articleList;

    public SgmConverter() {
        this.placesList = this.fillPlaces();
        this.articleList = new ArrayList<>();
    }

    List<String> fillPlaces(){
        List<String> placesList = new ArrayList<>();
        placesList.add("west-germany");
        placesList.add("usa");
        placesList.add("france");
        placesList.add("uk");
        placesList.add("canada");
        placesList.add("japan");

        return placesList;
    }

    void fillArticleList(String fileName){
        File input = new File("data/" + fileName + ".sgm");
        List<Document> reutersDocuments = getFiles(input);
        for (Document doc: reutersDocuments) {
//            System.out.println("=====================================");
//            System.out.println(doc);
            convertFile(doc);
        }
    }

    List<Document> getFiles(File input) {
        Document doc = null;
        try {
            doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            Elements reutersElements = doc.getElementsByTag("REUTERS");

            List<Document> reutersDocuments = new ArrayList<>();
            for (Element reutersElement : reutersElements) {
                String reutersHtml = reutersElement.toString();
                Document reutersDoc = Jsoup.parseBodyFragment(reutersHtml);
                reutersDocuments.add(reutersDoc);
            }

//            for (Document reutersDoc : reutersDocuments) {
//                System.out.println("=========================================");
//                System.out.println(reutersDoc);
//            }
            return reutersDocuments;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void convertFile(Document doc){
        List<String> articlePlaces = new ArrayList<>();
        boolean isIncluded = false;

            String places = doc.getElementsByTag("PLACES").text();

            for (String place: placesList)
            {
                if(places.contains(place)){
                    articlePlaces.add(place);
                    isIncluded = true;
                }
            }

            if (isIncluded){
                String content = doc.getElementsByTag("TEXT").text();
//                System.out.println("-----------------------------");
//                System.out.println(content);
//                System.out.println("-----------------------------");
                Article article = new Article(articlePlaces, content);
                this.articleList.add(article);
            }

    }

    public void getArticlesFromData(){
        String fileName = "reut2-0";
        String currFileName = "";
        for(int i = 0; i < 22; i++){
            if(i < 10){
                currFileName = fileName + "0" + i;
            }
            else{
                currFileName = fileName + i;
            }
            fillArticleList(currFileName);
        }
    }

    public ArrayList<Article> getArticleList() {
        return articleList;
    }

    public void showArticleList(){
        System.out.println("num of articles: " + getArticleListCount());
        for (Article article: articleList) {
            System.out.println("===");
            System.out.println("Places: " + article.getPlaces());
            System.out.println(article.getContent());
        }
    }

    public int getArticleListCount(){
        return this.articleList.size();
    }
}
