package com.company.processing;

import com.company.structures.LineSegment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rounder {

    public static final int decimalPlaces = 5;


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

    public static LineSegment round(LineSegment s){
        s.p1x = round(s.p1x);
        s.p1y = round(s.p1y);
        s.p2x = round(s.p2x);
        s.p2y = round(s.p2y);
        return s;
    }
}
