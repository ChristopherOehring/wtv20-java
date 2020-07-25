package com.wtv.processing;

import com.wtv.converter.CSVReader;
import com.wtv.converter.ObjConverter;
import com.wtv.structures.Point;

import java.io.IOException;
import java.util.*;

public class IsoSurface {
    public static void main(String[] args) // Eingabe: Filepath, Spaltentrenner(ohne space)
    {
        try {
            printIsoCubes("examples/sphere01.csv", 4);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void printIsoCubes(String fileName, int lineAmount) throws IOException {
        double[][][] result = CSVReader.dateiLesen3D("examples/sphere01.csv");
        assert result != null;
        double[] lineHeights = lineHeights(lineAmount, findMin(result), findMax(result));
        for(double h: lineHeights){
            ObjConverter.objCreateCubes(findIso(result, h), fileName.split("\\.")[0] + "(" + h + ")");
        }
    }

    public static double[] lineHeights(int amount, double min, double max) {
        double[] result = new double[amount];
        double interval = max - min;

        for(int i = 0; i < amount; i++){
            result[i] = min + (interval/(amount+1)) * (i+1);
        }
        return result;
    }

    /**
     * Uebung 7 1b
     * @param values a 3d array which may not be empty
     * @return the lowest value in the whole array
     */
    public static double findMin(double[][][] values){
        double min = Double.MAX_VALUE;
        for (double[][] arr2 : values) {
            for (double[] arr1: arr2) {
                for (double v: arr1) {
                    if(v < min) min = v;
                }
            }
        }
        return min;
    }

    /**
     * Uebung 7 1b
     * @param values a 3d array which may not be empty
     * @return the highest value in the whole array
     */
    public static double findMax(double[][][] values){
        double max = Double.MIN_VALUE;
        for (double[][] arr2 : values) {
            for (double[] arr1: arr2) {
                for (double v: arr1) {
                    if(v > max) max = v;
                }
            }
        }
        return max;
    }

    /**
     * Uebung 7 1c
     * @param values
     * @param iso
     * @return
     */
    public static Set<Point> findIso(double[][][] values, double iso) {
        Set<Point> result = new HashSet<>();

        for (int z = 0; z < values[0][0].length-1; z++) {
            for (int y = 0; y < values[0].length-1; y++) {
                for (int x = 0; x < values.length-1; x++ ) {
                    Point a = new Point(x,y,z,values[x][y][z]);
                    Point b;

                    b = new Point(x+1,y,z,values[x+1][y][z]);
                    result.add(intersectionOfLine(a,b,iso));

                    b = new Point(x,y+1,z,values[x][y+1][z]);
                    result.add(intersectionOfLine(a,b,iso));

                    b = new Point(x,y,z+1,values[x][y][z+1]);
                    result.add(intersectionOfLine(a,b,iso));
                }
            }
        }
        result.remove(null);
        return result;
    }


    /**
     * This is a subroutine of findIso. Returns the Point at which a linear interpolation between input a and b
     * reaches the value iso
     * @param a first point
     * @param b second point
     * @param iso the value to which we want to interpolate
     * @return Either returns the Point at which a linear interpolation between input a and b reaches the value iso,
     * or null if such point does not exist
     */
    public static Point intersectionOfLine(Point a, Point b, double iso) {
        double factor = (iso - a.value) / (b.value - a.value); //=NaN when dividing by zero
        Point p = null;
        if(0 <= factor && factor <=1) {
            p = new Point(
                    a.x + (factor * (b.x-a.x)),
                    a.y + (factor * (b.y-a.y)),
                    a.z + (factor * (b.z-a.z)),
                    iso);
        }
        return p;
    }
}
