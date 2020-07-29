package com.demo.Uebung4;

import com.wtv.processing.Isolinien;

public class Aufgabe1 {

    public static void main(String[] args) {
        try {
            String file = "examples\\map01_res3.csv";
            String seperator = ",";
            if(args.length > 0) {
                file = args[0];
            }
            if(args.length > 1) {
                seperator = args[1];
            }

            int anzLines = 5;

            Isolinien.printIsoByPath(file,seperator, anzLines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
