package com.company.converter;

import com.company.structures.LineSegment;
import com.company.processing.Rounder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

    public static void SVGCreateIso(Map<Double, List<LineSegment>> segments, String filePath, int width, int height){
        String s = createFile(filePath);
        writeFileIsoLines(segments, s, width, height);
    }

    private static String createFile(String filePath){
        filePath = filePath.split("\\.")[0];
        String newFilePath;
        newFilePath = filePath + ".svg";
        try {
            File file = new File(newFilePath);
            int x = 0;

            //Files überschreiben:

            //noinspection ResultOfMethodCallIgnored
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

    private static void writeFileIsoLines(Map<Double, List<LineSegment>> segments, String filePath, int width, int height){
        try {

            double thickness = ((double) width)/200;
            double scale = 1; //can be used to scale the output map

            StringBuilder stringBuilder = new StringBuilder("<svg xmlns=\"http://www.w3.org/2000/svg\" ")
                    .append("version=\"1.1\" x=\"1\" y=\"1\" ")
                    .append("width=\"").append(width*scale)
                    .append("\" height=\"").append(height*scale)
                    .append("\"> \n");

            /*
            The whole thing is pretty ineffective: It starts with a height array, from wich it generates an unsorted Map,
            from wich it gets an unsorted Set of heights, which is converted to an array, then sorted.
            I can't be bothered to change that thought.
             */
            Set<Double> h = segments.keySet();
            Double[] heights = new Double[h.size()];
            h.toArray(heights);
            Arrays.sort(heights);

            int i = 0;
            for(Double d: heights) {
                String color = lineColors(segments.size(), i);
                i++;
                for (LineSegment lS : segments.get(d)) {
                    stringBuilder
                            .append("<path d=\"M ")
                            .append(Rounder.round(lS.p1x)*scale).append(",").append(Rounder.round(lS.p1y)*scale)
                            .append(" l ").append(Rounder.round(lS.p2x - lS.p1x)*scale)
                            .append(",").append(Rounder.round(lS.p2y - lS.p1y)*scale)
                            .append("\" stroke=\"").append(color)
                            .append("\" stroke-width=\"").append(thickness*scale).append("\" /> \n");
                }
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

    private static String lineColors(int anz, int pos){
        String res = "rgb(";
        int max = 510;
        int intervall = max/anz;
        int x = pos*intervall;
        if(x < 255){
            res += "255,";
            res += x;
        } else {
            x = -x + 510;
            res += x;
            res += ",255";
        }
        res += ",0)";
        return res;
    }
}
