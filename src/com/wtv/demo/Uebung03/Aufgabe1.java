package com.wtv.demo.Uebung03;

import com.wtv.converter.CSVReader;
import com.wtv.converter.ObjConverter;
import com.wtv.demo.Demo;
import com.wtv.demo.DemoRunner;
import com.wtv.processing.Isolinien;
import com.wtv.structures.Pair;

import java.io.IOException;

import static com.wtv.processing.Isolinien.*;

public class Aufgabe1 implements Demo {
    public void demo(){
        Pair<String, String> pair = DemoRunner.getInputCsv("examples\\map03.csv", ",");
        String file = pair.getFirst();
        String seperator = pair.getSecond();

        try {
            double[][] list = CSVReader.dateiLesen2D(file, seperator);

            int anzLines = 5;
            int scale = 1;

            Isolinien.printIso(file,",", anzLines);
            ObjConverter.objCreateTriangles(triangles(scale(list, scale)), file);

        } catch (IOException e) {
            e.printStackTrace();
            demo();
        }
    }

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
