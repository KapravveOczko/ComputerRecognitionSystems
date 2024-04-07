package org.example;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public double euclideanMetrics(ArrayList<Double> v1, ArrayList<Double> v2, ArrayList<Double> wordComp) {

        double ans = 0.0;

        for (Double aDouble : wordComp) {
            ans += (1.0 - aDouble);
        }
        for (int i = 0; i < v1.size(); i++){
            ans += Math.pow((v1.get(i) - v2.get(i)), 2);
        }

        return Math.sqrt(ans);
    }

    public double streetMetrics(ArrayList<Double> v1, ArrayList<Double> v2, ArrayList<Double> wordComp) {

        double ans = 0.0;

        for (Double aDouble : wordComp) {
            ans += Math.pow((1.0 - aDouble), 2);
        }
        for (int i = 0; i < v1.size(); i++){
            ans += Math.abs(v1.get(i) - v2.get(i));
        }

        return ans;
    }

    public double czebyszewMetrics(ArrayList<Double> v1, ArrayList<Double> v2, ArrayList<Double> wordComp) {
        double ans = 0.0;

        for (Double value : wordComp) {
            if (ans < (1 - value)) {
                ans = (1 - value);
            }
        }
        for (int i = 0; i < v1.size(); i++) {
            double diff = Math.abs(v1.get(i) - v2.get(i));
            if (ans < diff) {
                ans = diff;
            }
        }

        return ans;
    }


    public double niewiadomskiMethod(String s1, String s2) {

        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int n1 = s1.length();
        int n2 = s2.length();
        int n = Math.max(n1, n2);
        double n1n2Result = 0.0;
        double n2n1Result = 0.0;

        if(n == 0){
            return 0.0;
        }

        for (int i = 1; i < n1; i++) {
            for (int j = 0; j < (n1 - i + 1); j++) {
                if (s2.contains(s1.substring(j, j + i))) {
                    n1n2Result++;
                }
            }
        }
        if(s2.contains(s1)){
            n1n2Result++;
        }

        for (int i = 1; i < n2; i++) {
            for (int j = 0; j < (n2 - i + 1); j++) {
                if (s1.contains(s2.substring(j, j + i))) {
                    n2n1Result += 1;
                }
            }
        }
        if(s1.contains(s2)){
            n2n1Result++;
        }

        return (2.0 / (n * n + n)) * (Math.min(n1n2Result, n2n1Result));
    }

    public ArrayList<Double> createWordComp(ArrayList<String> v1, ArrayList<String> v2){

        ArrayList<Double> wordComp = new ArrayList<>();
        for(int i=0; i<v1.size(); i++){
            wordComp.add(niewiadomskiMethod(v1.get(i), v2.get(i)));
//            System.out.println("nMetod: for " + " v1.get(i): " + v1.get(i) + " v2.get(i): " + v2.get(i) + " metod " + niewiadomskiMethod(v1.get(i), v2.get(i)));
        }

        return wordComp;
    }
}
