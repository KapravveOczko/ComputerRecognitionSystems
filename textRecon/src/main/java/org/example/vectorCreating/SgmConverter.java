package org.example.vectorCreating;

import org.example.DataObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.constants.Constants.*;

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

    public void fillArticleList(String fileName){
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

        String places = doc.getElementsByTag("PLACES").text();

        boolean isNotFound = !places.isEmpty() ? true : false;

        for (String place: places.split(" ")) {
            articlePlaces.add(place);
            if (!this.placesList.contains(place.toLowerCase())) {
                isNotFound = false;
            }
        }


        if (isNotFound){

            System.out.println("==========================");

            String content = doc.getElementsByTag("TEXT").text().replaceAll("[\n]", "");
//            try {
//                content = content.substring(0, doc.getElementsByTag("TITLE").text().length()) + content.substring(doc.getElementsByTag("TITLE").text().length() + doc.getElementsByTag("DATELINE").text().length()+1, content.length()-7);
                Article article = new Article(articlePlaces, content);
                System.out.println(places);
                this.articleList.add(article);
//            }
//            catch (Exception e){
//                return;
//            }
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
            System.out.println("converting: " + currFileName);
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
