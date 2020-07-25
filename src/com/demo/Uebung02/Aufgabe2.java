package com.demo.Uebung02;

import com.wtv.converter.CSVReader;
import com.wtv.converter.EPSConverter;
import com.wtv.converter.SVGConverter;
import com.wtv.processing.Isolinien;
import com.wtv.structures.LineSegment;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aufgabe2 {
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
