package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ObjConverter {
    public static void main(String[] args) {
        double[][] d;
        try {
            d = CSVReader.dateiLesenDyn(args[0], ",");
            System.out.println("Eingelesen");
            objCreateSingle(d, args[0]);
        } catch(IOException e){
            System.out.println("Error: Invalid input file");
            e.printStackTrace();
        }

    }

    public static void objCreateSingle(double[][] d, String filePath) {
        String newFilePath = createFile(filePath);
        writeFileSingle(d, newFilePath);
    }

    public static void objCreateTriangles(List<Triangle> triangles, String filePath){
        String newFilePath = createFile(filePath);
        writeFileTriangle(triangles, newFilePath);
    }

    private static String createFile(String filePath){
        filePath = filePath.split("\\.")[0];
        String newFilePath;
        newFilePath = filePath + ".obj";
        try {
            File file = new File(newFilePath);
            int x = 0;

            //Files überschreiben:
            if(!(file.createNewFile())){
                throw new IOException();
            }
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

    private static void writeFileTriangle(List<Triangle> triangles, String filePath){
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("o ").append(filePath).append("\n");

            int i = 1;
            for (Triangle t: triangles) {
                builder.append(String.format("v %1$s %2$s %3$s\n", t.a.x, t.a.y, t.a.z))
                        .append(String.format("v %1$s %2$s %3$s\n", t.b.x, t.b.y, t.b.z))
                        .append(String.format("v %1$s %2$s %3$s\n", t.c.x, t.c.y, t.c.z))
                        .append(String.format("f %1$s %2$s %3$s\n", i, (i+1), (i+2)));
                i = i+3;
            }


            FileWriter myWriter = new FileWriter(filePath, false);
            myWriter.write(builder.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void writeFileSingle(double[][] d, String filePath){
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
