package com.demo.Uebung03;

import com.wtv.converter.CSVReader;
import com.wtv.converter.ObjConverter;
import com.wtv.processing.Isolinien;

import java.util.Arrays;

import static com.wtv.processing.Isolinien.*;

public class Aufgabe1 {
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

            double[][] list = CSVReader.dateiLesen2D(file, seperator);

            int anzLines = 5;
            int scale = 1;

            Isolinien.printIso(file,",", anzLines);
            ObjConverter.objCreateTriangles(triangles(scale(list, scale)), file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
