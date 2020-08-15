package com.wtv.demo;

import com.wtv.demo.Uebung01.Aufgabe2a;
import com.wtv.demo.Uebung01.Aufgabe2b;
import com.wtv.demo.Uebung02.Aufgabe1;
import com.wtv.demo.Uebung02.Aufgabe2;
import com.wtv.structures.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class DemoRunner {
    public static Scanner scanner = new Scanner(System.in); // necessary because closing a scanner closes its input source...

    public static void main(String[] args) {

        HashMap<String, Demo> demos = new HashMap<>();
        demos.put("1:2a", new Aufgabe2a());
        demos.put("1:2b", new Aufgabe2b());
        demos.put("2:1", new Aufgabe1());
        demos.put("2:2", new Aufgabe2());
        demos.put("3:1", new com.wtv.demo.Uebung03.Aufgabe1());
        demos.put("4:1", new com.wtv.demo.Uebung4.Aufgabe1());
        demos.put("5:2", new com.wtv.demo.Uebung05.Aufgabe2());
        demos.put("7:1", new com.wtv.demo.Uebung07.Aufgabe1());

        outer: while(true) {
            System.out.println("Available Demos:");

            for (String s : demos.keySet().stream().sorted().collect(Collectors.toList())) {
                System.out.println("\t" + s);
            }
            String line;
            while (true) {
                System.out.print("Please choose one of the demos: ");
                line = scanner.nextLine()
                        .replaceAll("\\s+", "");
                if (demos.containsKey(line)) break;

                System.err.println("Invalid demo. Please try again.");
            }

            demos.get(line).demo();

            System.out.println("\n -- end of demo -- \n");
            while(true) {
                System.out.print("Do you want to run another demo?(J/n): ");
                line = scanner.nextLine()
                        .toLowerCase()
                        .replaceAll("\\s+", "");
                switch (line) {
                    case "no":
                    case "n":
                        break outer;

                    case "jes":
                    case "j":
                    case "":
                        continue outer;
                }
            }
        }
    }

    /**
     * Can be used to get the input through a commandline interface
     * @param filename default filename
     * @param seperator default line seperator
     * @return a {@link Pair} of filename and line seperator
     */
    public static Pair<String, String> getInputCsv(String filename, String seperator){
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
        return ret;
    }

    public static int getInputLineNumber(int number) {
        String line;
        while(true) {
            System.out.print("Number of Iso Lines (default = " + number + "): ");
            try {
                line = scanner.nextLine()
                        .replaceAll("\\s+", "");
                if(line.equals("")) return number;
                number = Integer.parseInt(line);
                return number;
            } catch (NumberFormatException e) {
                System.out.println();
                e.printStackTrace();
            }
        }
    }
}