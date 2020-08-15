package com.wtv.demo.Uebung01;

import com.wtv.converter.CSVReader;
import com.wtv.demo.Demo;
import com.wtv.demo.DemoRunner;
import com.wtv.structures.Pair;

import java.io.IOException;
import java.util.Arrays;

public class Aufgabe2a implements Demo {
    /**
     * Die Lösung der Aufgabe 2a
     */
    public void demo(){
        Pair<String, String> pair = DemoRunner.getInputCsv("examples\\test.csv", ",");
        String file = pair.getFirst();
        String seperator = pair.getSecond();

        double[][] list;
        try {
            list = CSVReader.dateiLesen2D(file, seperator);
            System.out.println(Arrays.deepToString(list).replace("], ", "]\n"));
        } catch (IOException e) {
            e.printStackTrace();
            demo();
        }
    }

    /**
     * Die Lösung der Aufgabe 2a
     * @param args Bei Angabe eines Parameters der Dateiname, bei Angabe von zweien der Dateiname und der Spaltentrenner
     */
    public static void main(String[] args) {
        try {
            String file = "examples\\test.csv";
            String seperator = ",";
            if(args.length > 0) {
                file = args[0];
            }
            if(args.length > 1) {
                seperator = args[1];
            }

            double[][] list = CSVReader.dateiLesen2D(file, seperator);
            System.out.println(Arrays.deepToString(list).replace("], ", "]\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
