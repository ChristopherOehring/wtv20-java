package com.wtv.demo;

import com.wtv.demo.Uebung01.Aufgabe2a;
import com.wtv.demo.Uebung01.Aufgabe2b;
import com.wtv.demo.Uebung02.Aufgabe1;
import com.wtv.demo.Uebung02.Aufgabe2;
import com.wtv.structures.Pair;

import java.util.*;

public class DemoRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<String, Demo> demos = new HashMap<>();
        demos.put("1:2a", new Aufgabe2a());
        demos.put("1:2b", new Aufgabe2b());
        demos.put("2:1", new Aufgabe1());
        demos.put("2:2", new Aufgabe2());
        demos.put("3:1", new com.wtv.demo.Uebung03.Aufgabe1());
        demos.put("4:1", new com.wtv.demo.Uebung4.Aufgabe1());
        demos.put("5:2", new com.wtv.demo.Uebung05.Aufgabe2());
        demos.put("7:1", new com.wtv.demo.Uebung07.Aufgabe1());

        System.out.println("Available Demos:");

        for(String s: demos.keySet()) {
            System.out.println("\t" + s);
        }
        String line;
        while (true) {
            System.out.print("Please choose one of the demos: ");
            line = scanner.nextLine();
            line = line.replaceAll("\\s+","");

            if(demos.containsKey(line)) break;

            System.out.println("Invalid demo. Please try again.");
        }

        demos.get(line).demo();
        scanner.close();
    }

    /**
     * Can be used to get the input through a commandline interface
     * @param filename default filename
     * @param seperator default line seperator
     * @return a {@link Pair} of filename and line seperator
     */
    public static Pair<String, String> getInputCsv(String filename, String seperator){
        Scanner scanner = new Scanner(System.in);
        String line;
        Pair<String, String> ret;

        System.out.print("Filename (default = " + filename + "): ");
        line = scanner.nextLine();
        if (!line.replaceAll("\\s+","").equals("")) {
            filename = line;
        }

        System.out.print("Line Seperator (default =  "+ seperator + "): ");
        line = scanner.nextLine();
        if (!line.replaceAll("\\s+","").equals("")) {
            seperator = line;
        }

        ret = new Pair<>(filename, seperator);
        scanner.close();
        return ret;
    }

    public static int getInputLineNumber(int number) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Number of Iso Lines (default = " + number + "): ");
            try {
                return Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
            } catch (NumberFormatException e) {
                System.out.println();
                e.printStackTrace();
            }
        }
    }
}