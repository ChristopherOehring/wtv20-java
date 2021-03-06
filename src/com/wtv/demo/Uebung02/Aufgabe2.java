package com.wtv.demo.Uebung02;

import com.wtv.converter.CSVReader;
import com.wtv.converter.EPSConverter;
import com.wtv.converter.SVGConverter;
import com.wtv.demo.Demo;
import com.wtv.demo.DemoRunner;
import com.wtv.processing.Isolinien;
import com.wtv.structures.LineSegment;
import com.wtv.structures.Pair;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aufgabe2 implements Demo {
    public void demo(){
        Pair<String, String> pair = DemoRunner.getInputCsv("examples\\U2A2a.csv", ",");
        String file = pair.getFirst();
        String seperator = pair.getSecond();

        try {
            double[][] list = CSVReader.dateiLesen2D(file, seperator);
            System.out.println(Arrays.deepToString(list).replace("], ", "]\n"));


            List<LineSegment> segments = CSVReader.arrayToSegments(list);
            Map<Double, List<LineSegment>> map = new HashMap<>();
            map.put(1.0, segments);
            SVGConverter.SVGCreateIsoFromSegments(map, file, (int) Isolinien.max(list) + 2, (int) Isolinien.max(list) + 2);

            EPSConverter.EPSCreate(file);

        } catch (Exception e) {
            e.printStackTrace();
            demo();
        }
    }

    public static void main(String[] args) {
        try {
            String file = "examples\\U2A2a.csv";
            String seperator = ",";
            if(args.length > 0) {
                file = args[0];
            }
            if(args.length > 1) {
                seperator = args[1];
            }

            double[][] list = CSVReader.dateiLesen2D(file, seperator);
            System.out.println(Arrays.deepToString(list).replace("], ", "]\n"));


            List<LineSegment> segments = CSVReader.arrayToSegments(list);
            Map<Double, List<LineSegment>> map = new HashMap<>();
            map.put(1.0, segments);
            SVGConverter.SVGCreateIsoFromSegments(map, file, (int) Isolinien.max(list) + 2, (int) Isolinien.max(list) + 2);

            EPSConverter.EPSCreate(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
