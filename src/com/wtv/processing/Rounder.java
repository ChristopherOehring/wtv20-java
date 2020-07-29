package com.wtv.processing;

import com.wtv.structures.LineSegment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rounder {

    public static final int decimalPlaces = 14;
    public static final int decimalPlacesString = 2;


    /**
     * Use this method to circumvent any issues resulting from incapability with scientific notation of doubles
     * @param value the value to be printed
     * @return a string, containing said value as a plain decimal number
     */
    public static String roundToString(double value) {
        if (decimalPlacesString < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimalPlacesString, RoundingMode.UP);
        return bd.toPlainString();
    }

    public static double round(double value) {
        return round(value, decimalPlaces);
    }

    public static double round(double value, int decimalPlaces) {
        if (decimalPlaces < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimalPlaces, RoundingMode.UP);
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

    public static LineSegment round(LineSegment s){
        return round(s, decimalPlaces);
    }

    public static LineSegment round(LineSegment s, int decimalPlaces){
        if (s==null) return null;
        s.p1x = round(s.p1x);
        s.p1y = round(s.p1y);
        s.p2x = round(s.p2x);
        s.p2y = round(s.p2y);
        return s;
    }
}
