package com.wtv.demo.Uebung05;

import com.wtv.converter.SVGConverter;
import com.wtv.demo.Demo;
import com.wtv.demo.DemoRunner;
import com.wtv.processing.Isolinien;
import com.wtv.structures.Pair;

import java.io.IOException;

public class Aufgabe2 implements Demo {
    public void demo(){
        Pair<String, String> pair = DemoRunner.getInputCsv("examples\\map03.csv", ",");
        String file = pair.getFirst();
        String seperator = pair.getSecond();

        int anzLines = DemoRunner.getInputLineNumber(5);

        try {
            SVGConverter.setCurvedMode(true);
            Isolinien.printIsoByPath(file,seperator, anzLines);

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

            int anzLines = 5;
            SVGConverter.setCurvedMode(true);
            Isolinien.printIsoByPath(file,seperator, anzLines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
