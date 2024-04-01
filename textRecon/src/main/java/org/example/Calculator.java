package org.example;

import java.util.List;

public class Calculator {

    public double euclideanMetrics(List<Double> v1, List<Double> v2, List<Double> wordComp) {

        double ans = 0.0;

        for (Double aDouble : wordComp) {
            ans += (1 - aDouble);
        }
        for (int i = 0; i < v1.size(); i++){
            ans += Math.pow((v1.get(i) - v2.get(i)), 2);
        }

        return Math.sqrt(ans);
    }

    public double streetMetrics(List<Double> v1, List<Double> v2, List<Double> wordComp) {

        double ans = 0.0;

        for (Double aDouble : wordComp) {
            ans += (1 - aDouble);
        }
        for (int i = 0; i < v1.size(); i++){
            ans += Math.abs(v1.get(i) - v2.get(i));
        }

        return ans;
    }

    public double czebyszewMetrics(List<Double> v1, List<Double> v2, List<Double> wordComp) {
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

        double n1 = s1.length();
        double n2 = s2.length();
        double n = Math.max(n1, n2);
        double n1n2Result = 0.0;
        double n2n1Result = 0.0;

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
}
