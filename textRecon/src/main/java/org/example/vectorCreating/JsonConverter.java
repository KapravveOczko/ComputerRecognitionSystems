package org.example.vectorCreating;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.DataObject;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
public class JsonConverter {

    // data to json
    public static void appendDataToJson(String fileName, ArrayList<Double> vector, ArrayList<String> wordVector, ArrayList<String> places) {
        // checking for file
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Wczytanie danych z pliku JSON do ArrayList
        ArrayList<DataObject> dataObjects = loadDataFromJson(fileName);

        // adding data to ArrayList
        DataObject newData = new DataObject(vector, wordVector, places);
        dataObjects.add(newData);

        // ArrayList to JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(dataObjects);

        // saving to json
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // from json to arraylist
    public static ArrayList<DataObject> loadDataFromJson(String fileName) {
        ArrayList<DataObject> dataObjects = new ArrayList<>();
        try (Reader reader = new FileReader(fileName)) {
            Type type = new TypeToken<ArrayList<DataObject>>() {}.getType();
            Gson gson = new Gson();
            dataObjects = gson.fromJson(reader, type);
            if (dataObjects == null) {
                dataObjects = new ArrayList<>(); // if list == null, creating new list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataObjects;
    }

}
