package org.example.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Constants {

    public static final int TP = 0;
    public static final int TN = 1;
    public static final int FP = 2;
    public static final int FN = 3;

    public static final int EUCLIDEAN = 1;
    public static final int STREET = 2;
    public static final int CZEBYSZEW = 3;


    public static final String USA = "usa";
    public static final String JAPAN = "japan";
    public static final String UK = "uk";
    public static final String FRANCE = "france";
    public static final String WEST_GERMANY = "west-germany";
    public static final String CANADA = "canada";
    public static final Set<String> COUNTRIES = new HashSet<>(Arrays.asList(USA, JAPAN, UK, FRANCE, WEST_GERMANY, CANADA));

}
