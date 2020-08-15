package com.demo.Uebung05;

import com.wtv.converter.SVGConverter;
import com.wtv.processing.Isolinien;

public class Aufgabe2 {
    public static void main(String[] args) {
        try {
            String file = "examples\\map03.csv";
            String seperator = ",";
            if(args.length > 0) {
                file = args[0];
            }
            if(args.length > 1) {
                seperator = args[1];
            }

            int anzLines = 5;
            SVGConverter.setCurvedMode(true);
            Isolinien.printIsoByPath(file,seperator, anzLines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
