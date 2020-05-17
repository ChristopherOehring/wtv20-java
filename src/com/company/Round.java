package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Round {

    public static final int decimalPlaces = 15;


    public static double round(double value) {
        if (decimalPlaces < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double[][] round(double[][] values){
        for(int i = 0; i < values.length; i++) {
            for(int j = 0; j < values[i].length; j++) {
                values[i][j] = round(values[i][j]);
            }
        }
        return values;
    }
}
