package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class EPSConverter {

    public static void main(String[] args) throws Exception {
        EPSCreate(args[0]);
    }

    public static void EPSCreate(String fileName) throws Exception{
        double [][] d = CSVReader.dateiLesenDyn(fileName, ",");
        LineSegment[] segments = new LineSegment[3];
        for(int i = 0; i < 3; i++){
            segments[i] = new LineSegment(d[i][0],d[i][1],d[i][2],d[i][3]);
        }
        for (LineSegment l:
                segments) {
            System.out.println(l);
        }

        String s = createFile(fileName);
        writeFile(segments, s);
    }

    private static String createFile(String filePath){
        filePath = filePath.split("\\.")[0];
        String newFilePath;
        newFilePath = filePath + ".eps";
        try {
            File file = new File(newFilePath);
            int x = 0;

            //Files überschreiben:
            file.createNewFile();

            //Files nicht Überschreiben:
          /*  while(!file.createNewFile()){
                System.out.println("File " + x + " already exists.");
                x++;
                newFilePath = filePath + "(" + x + ").eps";
                file = new File(newFilePath);
            }
            System.out.println("File created: " + file.getName());*/

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return newFilePath;
    }

    private static void writeFile(LineSegment[] s, String filePath){
        try {
            FileWriter myWriter = new FileWriter(filePath, false);
            String str = "%!PS-Adobe-3.0 EPSF-3.0\n" +
                    "%%BoundingBox: 7 7 53 33 \n" +
                    "6 setlinewidth \n";
            for (LineSegment l: s) {
                str = str + l.p1x + " " + l.p1y + " moveto\n" +
                        l.p2x + " " + l.p2y + " " + "lineto\n";
            }
            str += "closepath\n" +
                    "stroke";

            myWriter.write(str);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
