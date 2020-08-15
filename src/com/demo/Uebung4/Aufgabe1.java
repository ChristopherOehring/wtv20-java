package com.demo.Uebung4;

import com.wtv.converter.CSVReader;
import com.wtv.converter.SVGConverter;
import com.wtv.processing.Isolinien;

public class Aufgabe1 {

    public static void main(String[] args) {

        String file = "examples\\map03.csv";
        String seperator = ",";
        if(args.length > 0) {
            file = args[0];
        }
        if(args.length > 1) {
            seperator = args[1];
        }


        int anzLines = 5;
        SVGConverter.setVisualizePaths(true);

        try {
            Isolinien.printIsoByPath(file,seperator, anzLines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
