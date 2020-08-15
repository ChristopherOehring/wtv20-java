package com.wtv.demo.Uebung07;

import com.wtv.demo.Demo;
import com.wtv.demo.DemoRunner;
import com.wtv.processing.IsoSurface;
import com.wtv.structures.Pair;

import java.io.IOException;

public class Aufgabe1 implements Demo {
    public void demo(){
        Pair<String, String> pair = DemoRunner.getInputCsv("examples\\sphere01.csv", ",");
        String file = pair.getFirst();
        String seperator = pair.getSecond();

        try {
            int anzLines = DemoRunner.getInputLineNumber(5);
            IsoSurface.printIsoCubes(file, anzLines);

        } catch (IOException e) {
            e.printStackTrace();
            demo();
        }
    }

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
