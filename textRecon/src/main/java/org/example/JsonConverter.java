package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
public class JsonConverter {

    // Funkcja do dopisywania danych do pliku JSON
    public static void appendDataToJson(String fileName, ArrayList<Double> vector, ArrayList<String> wordVector, ArrayList<String> places) {
        // Sprawdzenie istnienia pliku
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

        // Dodanie nowego obiektu do ArrayList
        DataObject newData = new DataObject(vector, wordVector, places);
        dataObjects.add(newData);

        // Konwersja ArrayList na JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(dataObjects);

        // Zapisywanie danych do pliku JSON
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // z JSONa do ArrayList
    public static ArrayList<DataObject> loadDataFromJson(String fileName) {
        ArrayList<DataObject> dataObjects = new ArrayList<>();
        try (Reader reader = new FileReader(fileName)) {
            Type type = new TypeToken<ArrayList<DataObject>>() {}.getType();
            Gson gson = new Gson();
            dataObjects = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataObjects;
    }
}
