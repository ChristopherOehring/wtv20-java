package com.wtv.converter;

import com.wtv.processing.ColorGenerator;
import com.wtv.processing.Curves;
import com.wtv.processing.Rounder;
import com.wtv.structures.Knoten;
import com.wtv.structures.PathNode;
import com.wtv.structures.LineSegment;
import com.wtv.structures.Spot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SVGConverter {
    /**
     * Determines the thickness of the lines in the written SVGs <br>
     * Note: the thickness is also affected by {@link #scale}
     */
    public static double thickness = 0.05;

    /**
     * causes the lines to be curved with bezier curves. <br>
     * This only affects {@link #writeFileFromPath(Map, String, int, int)}
     */
    public static boolean curvedMode = false;

    /**
     * Causes individual paths to be visualized by differing thickness <br>
     * This only affects {@link #writeFileFromPath(Map, String, int, int)}
     */
    public static boolean visualizePaths = false;

    /**
     * This can be used to up/downscale the result.
     * Its 10 by default because the given examples can be pretty small otherwise.
     */
    public static double scale = 10.0;

    /**
     * This object is used for all Rounding Operations
     */
    public static Rounder rounder = new Rounder();

    public static void main(String[] args) throws Exception{
        SVGCreate(args[0]);
    }

    public static void SVGCreate(String fileName) throws Exception{
        double [][] d = CSVReader.dateiLesen2D(fileName, ",");
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

    public static void SVGCreateIsoFromSegments(Map<Double, List<LineSegment>> segments,
                                                String filePath, int width, int height){
        String s = createFile(filePath);
        writeFileFromSegments(segments, s, width, height);
    }

    public static void SVGCreateIsoFromPathNodes(Map<Double, List<PathNode>> linePaths,
                                                 String filePath, int width, int height) {
        String s = createFile(filePath);
        writeFileFromPathNodes(linePaths, s, width, height);
    }

    public static void SVGCreateIsoFromPath(Map<Double, List<Knoten>> linePaths,
                                                 String filePath, int width, int height) {
        String s = createFile(filePath);
        writeFileFromPath(linePaths, s, width, height);
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

    private static void writeFileFromSegments(Map<Double, List<LineSegment>> segments,
                                              String filePath, int width, int height){
        try {
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
                String color = ColorGenerator.arrayToColorString(ColorGenerator.lineColor(segments.size(), i));
                i++;
                for (LineSegment lS : segments.get(d)) {
                    stringBuilder
                            .append("<path d=\"M ")
                            .append(rounder.round(lS.p1x)*scale).append(",").append(rounder.round(lS.p1y)*scale)
                            .append(" l ").append(rounder.round(lS.p2x - lS.p1x)*scale)
                            .append(",").append(rounder.round(lS.p2y - lS.p1y)*scale)
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

    private static void writeFileFromPathNodes(Map<Double, List<PathNode>> linePaths,
                                               String filePath, int width, int height){
        try {

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
            Set<Double> h = linePaths.keySet();
            Double[] heights = new Double[h.size()];
            h.toArray(heights);
            Arrays.sort(heights);

            int i = 0;
            int p = 0;
            for(Double d: heights) { // iterate through each line height
                String color = ColorGenerator.arrayToColorString(ColorGenerator.lineColor(linePaths.size(), i));
                i++;
                stringBuilder.append("<path d=\"");
                p++;
                System.out.println("Path nr. " + p);
                for (PathNode path : linePaths.get(d)) { //iterate trough each path
                    Stack<PathNode> missed = new Stack<>();
                    missed.push(path);
                    while(!missed.empty()) { //iterate trough each branch
                        PathNode currentElement = missed.pop();
                        stringBuilder
                                .append("M ")
                                .append(rounder.roundToString(currentElement.getX() * scale))
                                .append(",")
                                .append(rounder.roundToString(currentElement.getY() * scale))
                                .append(" ");
                        while (currentElement.getFollowing().size()>0) { //iterate trough each element
                            PathNode nextNode = currentElement.getFollowing().iterator().next();
                            if(currentElement.getFollowing().size()>1) {
                                Set<PathNode> newFollowing = currentElement.getFollowing();
                                newFollowing.remove(nextNode);
                                currentElement.setFollowing(newFollowing);
                                missed.push(currentElement);
                            }
                            currentElement = nextNode;
                            stringBuilder
                                    .append("L ")
                                    .append(rounder.roundToString(currentElement.getX() * scale))
                                    .append(",")
                                    .append(rounder.roundToString(currentElement.getY() * scale))
                                    .append(" ");
                        }
                    }
                }
                stringBuilder
                        .append("\" stroke=\"").append(color)
                        .append("\" stroke-width=\"").append(rounder.roundToString(thickness)).append("\" fill=\"none\" /> \n");
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

    private static void writeFileFromPath(Map<Double, List<Knoten>> isoLines,
                                               String filePath, int width, int height){
        try {

            StringBuilder stringBuilder = new StringBuilder("<svg xmlns=\"http://www.w3.org/2000/svg\" ")
                    .append("version=\"1.1\" x=\"1\" y=\"1\" ")
                    .append("width=\"").append(width*scale)
                    .append("\" height=\"").append(height*scale)
                    .append("\"> \n");

            Set<Double> h = isoLines.keySet();
            Double[] heights = new Double[h.size()];
            h.toArray(heights);
            Arrays.sort(heights);

            int i = 0;
            int p = 0;
            for(Double d: heights) { // iterate through each line height
                double thickness = SVGConverter.thickness;
                String color = ColorGenerator.arrayToColorString(ColorGenerator.lineColor(isoLines.size(), i));
                i++;

                //set of all paths
                Set<LinkedList<Spot>> paths = new HashSet<>();
                for (Knoten k : isoLines.get(d)) {
                    paths.addAll(k.getPaths());
                }


                for (LinkedList<Spot> spots: paths) {
                    stringBuilder.append("\t<path d=\"");
                    p++;
                    System.out.println("Path nr. " + p);
                    Spot currentElement;
                    if(!curvedMode || spots.size() <= 2) {
                        currentElement = spots.pop();
                        stringBuilder
                                .append("M ")
                                .append(rounder.roundToString(currentElement.getX() * scale))
                                .append(",")
                                .append(rounder.roundToString(currentElement.getY() * scale))
                                .append(" ");
                        while (!spots.isEmpty()) {
                            currentElement = spots.pop();
                            stringBuilder
                                    .append("L ")
                                    .append(rounder.roundToString(currentElement.getX() * scale))
                                    .append(",")
                                    .append(rounder.roundToString(currentElement.getY() * scale))
                                    .append(" ");
                        }
                    }
                    else {
                        spots = Curves.getControlPoints(spots);
                        currentElement = spots.pop();
                        stringBuilder
                                .append("M ")
                                .append(rounder.roundToString(currentElement.getX() * scale))
                                .append(",")
                                .append(rounder.roundToString(currentElement.getY() * scale))
                                .append(" ");
                        while (!spots.isEmpty()) {
                            currentElement = spots.pop();
                            stringBuilder
                                    .append("C ")
                                    .append(rounder.roundToString(currentElement.getX() * scale)).append(" ")
                                    .append(rounder.roundToString(currentElement.getY() * scale)).append(", ");
                            currentElement = spots.pop();
                            stringBuilder
                                    .append(rounder.roundToString(currentElement.getX() * scale)).append(" ")
                                    .append(rounder.roundToString(currentElement.getY() * scale)).append(", ");
                            currentElement = spots.pop();
                            stringBuilder
                                    .append(rounder.roundToString(currentElement.getX() * scale)).append(" ")
                                    .append(rounder.roundToString(currentElement.getY() * scale)).append(" ");
                        }
                    }
                    stringBuilder
                            .append("\" stroke=\"").append(color)
                            .append("\" stroke-width=\"").append(rounder.roundToString(scale* thickness)).append("\" fill=\"none\" /> \n");

                    if (visualizePaths) thickness = thickness + 0.02;
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

    public static double getThickness() {
        return thickness;
    }

    public static void setThickness(double thickness) {
        SVGConverter.thickness = thickness;
    }

    public static boolean isCurvedMode() {
        return curvedMode;
    }

    public static void setCurvedMode(boolean curvedMode) {
        SVGConverter.curvedMode = curvedMode;
    }

    public static boolean isVisualizePaths() {
        return visualizePaths;
    }

    public static void setVisualizePaths(boolean visualizePaths) {
        SVGConverter.visualizePaths = visualizePaths;
    }

    public static double getScale() {
        return scale;
    }

    public static void setScale(double scale) {
        SVGConverter.scale = scale;
    }

    private static void writeFileLines2(List<LineSegment> segments,
                                        String filePath, int width, int height){
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

    public static Rounder getRounder() {
        return rounder;
    }

    public static void setRounder(Rounder rounder) {
        SVGConverter.rounder = rounder;
    }
}
