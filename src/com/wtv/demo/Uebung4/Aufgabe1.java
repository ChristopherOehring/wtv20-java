package com.wtv.demo.Uebung4;

import com.wtv.converter.SVGConverter;
import com.wtv.demo.Demo;
import com.wtv.demo.DemoRunner;
import com.wtv.processing.Isolinien;
import com.wtv.structures.Pair;

import java.io.IOException;

public class Aufgabe1 implements Demo{
    public void demo(){
        Pair<String, String> pair = DemoRunner.getInputCsv("examples\\map03.csv", ",");
        String file = pair.getFirst();
        String seperator = pair.getSecond();

        try {
            int anzLines = DemoRunner.getInputLineNumber(5);
            SVGConverter.setVisualizePaths(true);

            Isolinien.printIsoByPath(file,seperator, anzLines);
        } catch (IOException e) {
            e.printStackTrace();
            demo();
        }
    }

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
