package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ObjConverter {
    public static void main(String[] args) {
        double[][] d;
        try {
            d = CSVReader.dateiLesenDyn(args[0], ",");
            System.out.println("Eingelesen");
            objCreate(d, args[0]);
        } catch(IOException e){
            System.out.println("Error: Invalid input file");
            e.printStackTrace();
        }

    }

    public static void objCreate(double[][] d, String filePath) {
        String newFilePath = createFile(filePath);
        writeFile(d, newFilePath);

    }

    private static String createFile(String filePath){
        filePath = filePath.split("\\.")[0];
        String newFilePath;
        newFilePath = filePath + ".obj";
        try {
            File file = new File(newFilePath);
            int x = 0;

            //Files überschreiben:
            file.createNewFile();

           /* Files nicht Überschreiben:
            while(!file.createNewFile()){
                System.out.println("File " + x + " already exists.");
                x++;
                newFilePath = filePath + "(" + x + ").obj";
                file = new File(newFilePath);
            }*/
            System.out.println("File created: " + file.getName());

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return newFilePath;
    }

    private static void writeFile(double[][] d, String filePath){
        try {
            FileWriter myWriter = new FileWriter(filePath, false);
            double midpoint = d[0][0] + d[0][1] +d[1][0] + d[1][1];
            midpoint = midpoint/4;
            myWriter.write("o " + filePath+ "\n" +
                    "v 1 0 " + d[0][0] + "\n" +
                    "v 1 1 " + d[0][1] + "\n" +
                    "v 0 0 " + d[1][0] + "\n" +
                    "v 0 1 " + d[1][1] + "\n" +
                    "v 0.5 0.5 " + midpoint + "\n" +
                    "f 1 2 5 \n" +
                    "f 1 3 5 \n" +
                    "f 3 4 5 \n" +
                    "f 2 4 5 \n"
            );
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
