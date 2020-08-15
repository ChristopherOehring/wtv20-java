package com.wtv.demo.Uebung02;

import com.wtv.demo.Demo;
import com.wtv.demo.DemoRunner;
import com.wtv.processing.Interpolation;
import com.wtv.structures.Pair;

import java.util.Scanner;

public class Aufgabe1 implements Demo {

    public void demo(){
        Scanner scanner = DemoRunner.scanner;
        String line;
        Pair<String, String> ret;
        double v0;
        double v1;
        double x;

        while (true) {
            System.out.print("v0:");
            line = scanner.nextLine().replaceAll("\\s+","");
            try {
                v0 = Double.parseDouble(line);
                break;
            } catch (NumberFormatException e) {
                System.out.println();
                e.printStackTrace();
            }
        }

        while (true) {
            System.out.print("v1:");
            line = scanner.nextLine().replaceAll("\\s+","");
            try {
                v1 = Double.parseDouble(line);
                break;
            } catch (NumberFormatException e) {
                System.out.println();
                e.printStackTrace();
            }
        }

        while (true) {
            System.out.print("x:");
            line = scanner.nextLine().replaceAll("\\s+","");
            try {
                x = Double.parseDouble(line);
                break;
            } catch (NumberFormatException e) {
                System.out.println();
                e.printStackTrace();
            }
        }

        Interpolation.compare(v0, v1, x);
    }

    public static void main(String[] args) {
        double[] values = {2,3,2};
        if(args.length >= 3) {
            for(int i = 0; i < 3; i++){
                values[i] = Double.parseDouble(args[i]);
            }
        }
        Interpolation.compare(values[0], values[1], values[2]);
    }

}
