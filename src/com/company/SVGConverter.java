package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SVGConverter {
    public static void main(String[] args) throws Exception{
        SVGCreate(args[0]);
    }

    public static void SVGCreate(String fileName) throws Exception{
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
        newFilePath = filePath + ".svg";
        try {
            File file = new File(newFilePath);
            int x = 0;

            //Files überschreiben:
            file.createNewFile();

            //Files nicht Überschreiben:
          /*  while(!file.createNewFile()){
                System.out.println("File " + x + " already exists.");
                x++;
                newFilePath = filePath + "(" + x + ").svg";
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
            myWriter.write( "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" x=\"7\" y=\"7\" width=\"300\" height=\"300\"> \n" +
                     "<polygon \n" +
                    " points=\"" + s[0].p1x+","+s[0].p1y + " " + s[1].p1x+","+s[1].p1y + " " + s[2].p1x+","+s[2].p1y + "\"" +
                    " stroke=\"black\"\n" +
                    " stroke-width=\"3\"\n" +
                    " fill=\"none\" />\n" +
                    "</svg>"
            );
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
