package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SVGConverter {
    public static void main(String[] args) throws Exception{
        SVGCreate(args[0]);
    }

    public static void SVGCreate(String fileName) throws Exception{
        double [][] d = CSVReader.dateiLesenDyn(fileName, ",");
        List<LineSegment> segments = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            segments.add(new LineSegment(d[i][0],d[i][1],d[i][2],d[i][3]));
        }
        for (LineSegment l:
                segments) {
            System.out.println(l);
        }

        String s = createFile(fileName);
        writeFileTriangle(segments, s);
    }

    public static void SVGCreate(List<LineSegment> segments, String filePath, int width, int height){
        String s = createFile(filePath);
        writeFileLines(segments, s, width, height);
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
            /*
            while(!file.createNewFile()){
                System.out.println("File " + x + " already exists.");
                x++;
                newFilePath = filePath + "(" + x + ").svg";
                file = new File(newFilePath);
            }
            System.out.println("File created: " + file.getName());
            */

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return newFilePath;
    }

    private static void writeFileTriangle(List<LineSegment> s, String filePath){
        try {
            FileWriter myWriter = new FileWriter(filePath, false);
            String file = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" x=\"7\" y=\"7\" width=\"300\" height=\"300\"> \n" +
                    "<polygon \n" +
                    " points=\"" + s.get(0).p1x+","+s.get(0).p1y + " " + s.get(1).p1x+","+s.get(1).p1y + " " + s.get(2).p1x+","+s.get(2).p1y + "\"" +
                    " stroke=\"black\"\n" +
                    " stroke-width=\"3\"\n" +
                    " fill=\"none\" />\n" +
                    "</svg>";

            myWriter.write(file);
            myWriter.close();


            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void writeFileLines(List<LineSegment> segments, String filePath, int width, int height){
        try {

            double thickness = ((double) width)/200;

            StringBuilder stringBuilder = new StringBuilder("<svg xmlns=\"http://www.w3.org/2000/svg\" ")
                    .append("version=\"1.1\" x=\"1\" y=\"1\" ")
                    .append("width=\"").append(width)
                    .append("\" height=\"").append(height)
                    .append("\"> \n");

            for (LineSegment lS: segments){
                stringBuilder
                        .append("<path d=\"M ")
                        .append(Rounder.round(lS.p1x)).append(",").append(Rounder.round(lS.p1y))
                        .append("l ").append(Rounder.round(lS.p2x-lS.p1x)).append(",").append(Rounder.round(lS.p2y-lS.p1y))
                        .append("\" stroke=\"black\" stroke-width=\"").append(thickness).append("\" /> \n");
            }
            stringBuilder.append("</svg>");

            String file = stringBuilder.toString();

            FileWriter myWriter = new FileWriter(filePath, false);
            myWriter.write(file);
            myWriter.close();


            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void writeFileLines2(List<LineSegment> segments, String filePath, int width, int height){
        try {

            StringBuilder stringBuilder = new StringBuilder("<svg xmlns=\"http://www.w3.org/2000/svg\" ")
                    .append("version=\"1.1\" x=\"1\" y=\"1\" ")
                    .append("width=\"").append(width)
                    .append("\" height=\"").append(height)
                    .append("\"> \n");

            for (LineSegment lS: segments){
                stringBuilder
                        .append("<line ")
                        .append("x1=\"").append(lS.p1x).append("\" y1=\"").append(lS.p1y).append("\" ")
                        .append("x2=\"").append(lS.p2x).append("\" y2=\"").append(lS.p2y).append("\" ")
                        .append("stroke=\"black\" stroke-width=\"1\"/> \n");
            }
            stringBuilder.append("</svg>");

            String file = stringBuilder.toString();

            FileWriter myWriter = new FileWriter(filePath, false);
            myWriter.write(file);
            myWriter.close();


            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
