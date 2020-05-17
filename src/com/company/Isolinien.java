package com.company;

import com.company.U2A2.LineSegment;

import java.util.*;


public class Isolinien {

    public static void main(String[] args) throws Exception {
        System.out.println(triangles(CSVReader.dateiLesenDyn(args[0], ",")));
    }

    public static double max(double[][] values){
        return Arrays.stream(values)                                // -> double[] stream
                .map(innerList -> Arrays.stream(innerList).max())   // -> OptionalDouble Stream
                .map(d -> d.getAsDouble())                          // -> Double stream
                .max(Double::compareTo)                             // -> Optional<Double>
                .get()                                              // -> Double
                .doubleValue();                                     // -> double
    }

    public static double min(double[][] values){
        return Arrays.stream(values)                                // -> double[] stream
                .map(innerList -> Arrays.stream(innerList).min())   // -> OptionalDouble Stream
                .map(d -> d.getAsDouble())                          // -> Double stream
                .min(Double::compareTo)                             // -> Optional<Double>
                .get()                                              // -> Double
                .doubleValue();                                     // -> double
    }

    public static double[][] move(double[][] values, double move) {
        for(int i = 0; i < values.length; i++) {
            for(int j = 0; j < values[i].length; j++) {
                values[i][j] = values[i][j] + move;
            }
        }
        return values;
    }

    public static double[][] scale(double[][] values, double scale) {

        for(int i = 0; i < values.length; i++) {
            for(int j = 0; j < values[i].length; j++) {
                values[i][j] = values[i][j] * scale;

            }
        }
        return values;
    }

    public static double[] lineHights(int amount, double min, double max) {
        double[] result = new double[amount];
        double intervall = max - min;

        for(int i = 0; i < amount; i++){
            result[i] = (intervall/(amount+1)) * i+1;
        }
        return result;
    }

    public static List<Triangle> triangles(double[][] values){
        List<Triangle> triangles = new ArrayList<>();
        for(int invY = 0; invY < values.length - 1; invY++){
            for (int x = 0; x < values[invY].length - 1; x++){
                int y = values.length - invY + 1;
                double midPoint = midPoint(values,x,invY);

                triangles.add(
                        new Triangle(
                                new Point(x,      y,         values[invY][x]),
                                new Point(x+1,    y,         values[invY][x+1]),
                                new Point(x - 0.5,y - 0.5,   midPoint)));

                triangles.add(
                        new Triangle(
                                new Point(x+1,    y,         values[invY][x]),
                                new Point(x+1,    y-1,       values[invY][x+1]),
                                new Point(x - 0.5,y - 0.5,   midPoint)));

                triangles.add(
                        new Triangle(
                                new Point(x+1,    y-1,       values[invY][x]),
                                new Point(x,      y-1,       values[invY][x+1]),
                                new Point(x - 0.5,y - 0.5,   midPoint)));

                triangles.add(
                        new Triangle(
                                new Point(x,      y,         values[invY][x]),
                                new Point(x,      y-1,       values[invY][x+1]),
                                new Point(x - 0.5,y - 0.5,   midPoint)));

            }
        }
        return triangles;
    }

    public static double midPoint(double[][] values, int x, int y){
        return (values[y][x] + values[y+1][x] + values[y][x+1] + values[y+1][x+1]) / 4.0;
    }

    public static List<LineSegment> getIsoLine(List<Triangle> triangles, double lineHight) {
        List<LineSegment> result = new ArrayList<>();
        for(Triangle t: triangles){

        }
        return result;
    }

    // TODO: 12.05.2020 implementieren (Uebung 3, Aufgabe 1f) 
    private Optional<LineSegment> intersection(Triangle triangle, double lineHight) {
        if(triangle.a.z > lineHight) {
            if(triangle.b.z > lineHight) {
                if(triangle.c.z < lineHight){
                    double middle = (lineHight - triangle.c.z) / (triangle.a.z - triangle.c.z);

                }
                else return null;
            }
        }
        return null;
    }

}


