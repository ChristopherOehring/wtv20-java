package com.company.processing;

import com.company.converter.CSVReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IsoSurface {
    public static void main(String[] args) // Eingabe: Filepath, Spaltentrenner(ohne space)
    {
        try {
            double[][][] result = CSVReader.dateiLesen3D("examples/sphere01.csv");
            int length = result.length;
            for (int z = 0; z < length; z++){

                for (int y = 0; y < length; y++){
                    System.out.println();
                    for (int x = 0; x < length; x++)
                    {
                        System.out.print(result[x][y][z] + ", \t");
                    }
                }
                System.out.println();
            }
            System.out.println(findMin(result));
            System.out.println(findMax(result));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     *
     * @param values may not be empty
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
     *
     * @param values may not be empty
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
}
