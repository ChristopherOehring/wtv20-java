package com.company;

import java.util.*;

public class Isolinien {

    public static void main(String[] args) throws Exception {
        /*
        Point a = new Point(0,0,0);
        Point b = new Point(1,0,1);
        Point c = new Point(0.5,0.5,1);
        Triangle t = new Triangle(a,b,c);
        System.out.println(intersectionOfTriangle(t, 1));
         */


        int anzLines = 10;
        List<String> x = new ArrayList<>();

        x.add("map01_res3.csv");
        x.add("hill_res50.csv");
        x.add("map03.csv");
        x.add("saddle_res50.csv");
        x.add("tilt_D_res50.csv");

        for(String s : x){
            printIso(s,",", anzLines);
            ObjConverter.objCreateTriangles(triangles(CSVReader.dateiLesenDyn(s, ",")), s);
        }

    }

    public static void printIso(String file, String spaltentrenner, int amount) throws Exception{
        double[][] d = CSVReader.dateiLesenDyn(file, spaltentrenner);
        //ObjConverter.objCreateTriangles(triangles(d), file);

        Map<Double, List<LineSegment>> segments = getIsolinienForArray(d, amount);
        SVGConverter.SVGCreateIso(segments, file, d.length-1, d[0].length -1);
    }

    public static Map<Double, List<LineSegment>> getIsolinienForArray(double[][] values, int amount){
        double min = min(values);
        double max = max(values);
        double[] heights = lineHeights(amount, min, max);
        Map<Double, List<LineSegment>> result = new HashMap<>();
        List<Triangle> triangles = triangles(values);
        for(double h: heights) {
            result.put(h, getIsoLine(triangles,h));
        }

        return result;
    }

    public static double max(double[][] values) throws NoSuchElementException{
        //noinspection OptionalGetWithoutIsPresent
        return Arrays.stream(values)                                // -> double[] stream
                .map(innerList -> Arrays.stream(innerList).max())   // -> OptionalDouble Stream
                .map(OptionalDouble::getAsDouble)                   // -> Double stream
                .max(Double::compareTo)                             // -> Optional<Double>
                .get();                                             // -> Double
    }

    public static double min(double[][] values) throws NoSuchElementException{
        //noinspection OptionalGetWithoutIsPresent
        return Arrays.stream(values)                                // -> double[] stream
                .map(innerList -> Arrays.stream(innerList).min())   // -> OptionalDouble Stream
                .map(OptionalDouble::getAsDouble)                          // -> Double stream
                .min(Double::compareTo)                             // -> Optional<Double>
                .get();                                             // -> Double
    }

    public static double[][] move(double[][] values, double move) {
        for(int i = 0; i < values.length; i++) {
            for(int j = 0; j < values[i].length; j++) {
                values[i][j] = values[i][j] + move;
            }
        }
        return values;
    }

    public static double[][] scale(double[][] values, double scale) {

        for(int i = 0; i < values.length; i++) {
            for(int j = 0; j < values[i].length; j++) {
                values[i][j] = values[i][j] * scale;

            }
        }
        return values;
    }

    public static double[] lineHeights(int amount, double min, double max) {
        double[] result = new double[amount];
        double intervall = max - min;

        for(int i = 0; i < amount; i++){
            result[i] = min + (intervall/(amount+1)) * (i+1);
        }
        return result;
    }

    public static List<Triangle> triangles(double[][] values){
        List<Triangle> triangles = new ArrayList<>();
        /*
        The Idea of the following loop is  to go through all squares within the table of values and add the 3
        corresponding triangles for each.
        The (x,y) is always the top left corner of the square
         */
        for(int x = 0; x < values.length - 1; x++){         //iterate through y coordinates
//            System.out.println(x+ ", " + values.length);
            for (int y = 0; y < values[x].length - 1; y++){ //iterate through x coordinates
//                System.out.println(":" + y + ", " + values[x].length);
                double midPoint = middlePoint(values,x,y);

                triangles.add(
                        new Triangle(
                                new Point(x,      y,         values[x][y]),
                                new Point(x+1,    y,         values[x+1][y]),
                                new Point(x + 0.5,y + 0.5,   midPoint)));

                triangles.add(
                        new Triangle(
                                new Point(x+1,    y,         values[x+1][y]),
                                new Point(x+1,    y+1,       values[x+1][y+1]),
                                new Point(x + 0.5,y + 0.5,   midPoint)));

                triangles.add(
                        new Triangle(
                                new Point(x+1,    y+1,       values[x+1][y+1]),
                                new Point(x,      y+1,       values[x][y+1]),
                                new Point(x + 0.5,y + 0.5,   midPoint)));

                triangles.add(
                        new Triangle(
                                new Point(x,      y,         values[x][y]),
                                new Point(x,      y+1,       values[x][y+1]),
                                new Point(x + 0.5,y + 0.5,   midPoint)));

            }
        }

        for (Triangle t: triangles) {
            t.a.z = Rounder.round(t.a.z);
            t.b.z = Rounder.round(t.b.z);
            t.c.z = Rounder.round(t.c.z);

        }

        return triangles;
    }

    public static double middlePoint(double[][] values, int x, int y){
        return ((values[x][y] + values[x+1][y] + values[x][y+1] + values[x+1][y+1]) / 4.0);
    }

    public static List<LineSegment> getIsoLine(List<Triangle> triangles, double lineHeight) {
        List<LineSegment> result = new ArrayList<>();
        for(Triangle t: triangles){
            LineSegment s = intersectionOfTriangle(t, lineHeight);
            if(s != null){
                //s = Rounder.round(s);
                result.add(s);
            }
        }
        return result;
    }

    private static LineSegment intersectionOfTriangle(Triangle triangle, double lineHeight) {
        List<Point> points = new ArrayList<>();
        Point a = intersectionOfLine(triangle.a, triangle.b, lineHeight);
        if(a != null) {
            points.add(a);
        }
        a = intersectionOfLine(triangle.b, triangle.c, lineHeight);
        if(a != null) {
            points.add(a);
        }
        a = intersectionOfLine(triangle.c, triangle.a, lineHeight);
        if(a != null) {
            points.add(a);
        }
        if(points.size() == 2 &&
                (points.get(0).x != points.get(1).x ||
                        points.get(0).y != points.get(1).y)){
            return new LineSegment(
                    points.get(0).x, points.get(0).y,
                    points.get(1).x, points.get(1).y);
        }
        return null;
    }

    /**
     *
     * @param a first point
     * @param b second point
     * @param lineHeight the z value
     * @return Returns the point at witch the line between the two points reaches a certain height.
     *          Returns zero if the intersection is not a point
     */
    public static Point intersectionOfLine(Point a, Point b, double lineHeight) {
        double factor = (lineHeight - a.z) / (b.z - a.z); //=NaN when dividing by zero
        Point p = null;
        if(0 <= factor && factor <=1) {
            p = new Point(
                    a.x + (factor * (b.x-a.x)),
                    a.y + (factor * (b.y-a.y)),
                    a.z + (factor * (b.z-a.z)));
        }
        return p;
    }


}


