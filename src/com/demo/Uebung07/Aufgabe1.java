package com.demo.Uebung07;

import com.wtv.converter.SVGConverter;
import com.wtv.processing.IsoSurface;
import com.wtv.processing.Isolinien;

public class Aufgabe1 {
    public static void main(String[] args) {
        try {
            String file = "examples\\sphere01.csv";
            if(args.length > 0) {
                file = args[0];
            }

            int anzLines = 5;
            IsoSurface.printIsoCubes(file, anzLines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
