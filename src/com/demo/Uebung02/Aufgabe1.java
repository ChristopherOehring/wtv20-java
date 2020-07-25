package com.demo.Uebung02;

import com.wtv.processing.Interpolation;

public class Aufgabe1 {
    public static void main(String[] args) {
        double[] values = {2,3,2};
        if(args.length >= 3) {
            for(int i = 0; i < 3; i++){
                values[i] = Double.parseDouble(args[i]);
            }
        }
        Interpolation.compare(values[0], values[1], values[2]);
    }
}
