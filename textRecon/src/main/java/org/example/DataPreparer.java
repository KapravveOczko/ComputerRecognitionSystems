package org.example;

import java.util.ArrayList;

public class DataPreparer {

    private ArrayList<DataObject> learningData;
    private ArrayList<DataObject> testData;

    public DataPreparer(ArrayList<DataObject> fullData, int percentage) {
         separateData(fullData, percentage);
    }

    public ArrayList<DataObject> getLearningData() {
        return learningData;
    }

    public void setLearningData(ArrayList<DataObject> learningData) {
        this.learningData = learningData;
    }

    public ArrayList<DataObject> getTestData() {
        return testData;
    }

    public void setTestData(ArrayList<DataObject> testData) {
        this.testData = testData;
    }

//private

    private void separateData(ArrayList<DataObject> fullData, int percentage){
        ArrayList<DataObject> testData = new ArrayList<>();
        ArrayList<DataObject> learnData = new ArrayList<>();

        for(int i = 0; i<(int) (fullData.size() * percentage/100); i++){
            testData.add(fullData.get(i));
        }
        for(int i = testData.size(); i< fullData.size(); i++){
            learnData.add(fullData.get(i));
        }

        setTestData(testData);
        setLearningData(learnData);
    }
}
