package com.demo.Uebung01;

import com.wtv.converter.CSVReader;
import com.wtv.converter.ObjConverter;
import com.wtv.processing.Isolinien;
import com.wtv.structures.Triangle;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Aufgabe2b {

    /**
     * Die Lösung der Aufgabe 2b
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

            List<Triangle> triangles = Isolinien.triangles(list);
            ObjConverter.objCreateTriangles(triangles, file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
