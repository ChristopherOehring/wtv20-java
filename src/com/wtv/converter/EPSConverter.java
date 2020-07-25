package com.wtv.converter;

import com.wtv.structures.LineSegment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EPSConverter {

    public static void main(String[] args) throws Exception {
        EPSCreate(args[0]);
    }

    public static void EPSCreate(String fileName) throws Exception{
        double [][] d = CSVReader.dateiLesen2D(fileName, ",");
        LineSegment[] segments = new LineSegment[3];
        for(int i = 0; i < 3; i++){
            segments[i] = new LineSegment(d[i][0],d[i][1],d[i][2],d[i][3]);
        }
        for (LineSegment l:
                segments) {
            System.out.println(l);
        }

        String s = createFile(fileName);
        writeFile(Arrays.asList(segments), s);
    }

    private static String createFile(String filePath){
        filePath = filePath.split("\\.")[0];
        String newFilePath;
        newFilePath = filePath + ".eps";
        try {
            File file = new File(newFilePath);
            int x = 0;

            //Files überschreiben:
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return newFilePath;
    }

    // TODO: 5/26/20 Introduce stringBuilder
    private static void writeFile(List<LineSegment> s, String filePath){
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for (LineSegment i: s) {
            if (i.p1x > maxX) maxX = i.p1x;
            if (i.p2x > maxX) maxX = i.p2x;
            if (i.p1x < minX) minX = i.p1x;
            if (i.p2x < minX) minX = i.p2x;

            if (i.p1y > maxY) maxY = i.p1y;
            if (i.p2y > maxY) maxY = i.p2y;
            if (i.p1y < minY) minY = i.p1y;
            if (i.p2y < minY) minY = i.p2y;
        }
        double offset = 3;

        minX += offset;
        minY -= offset;
        maxX += offset;
        minY -= offset;

        try {
            FileWriter myWriter = new FileWriter(filePath, false);
            String str = "%!PS-Adobe-3.0 EPSF-3.0\n" +
                    "%%BoundingBox: " + minX + " " + minY + " " + maxX + " " + maxY + "\n" +
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

