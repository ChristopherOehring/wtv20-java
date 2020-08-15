package com.wtv.converter;

import com.wtv.processing.Rounder;
import com.wtv.structures.Point;
import com.wtv.structures.Square;
import com.wtv.structures.Triangle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ObjConverter {

    public static Rounder rounder = new Rounder();

    public static void objCreateTriangles(List<Triangle> triangles, String filePath) {
        String newFilePath = createFileRawInput(filePath);
        writeFileTriangles(triangles, newFilePath);
    }

    /**
     * Result of Uebung 7 1d <br>
     * Using Tetrahedrons leads to very weird results. I don't see the point <br>
     * <img src="./doc-files/tetrahedrons.png" alt="see img: tetrahedrons.png">
     *
     * @param points
     * @param filePath
     */
    public static void objCreateTetrahedrons(Set<Point> points, String filePath) {
        String newFilePath = createFileRawInput(filePath);
        writeFileTetrahedrons(points, newFilePath);
    }

    /**
     * Result of Uebung 7 1d  <br>
     * Example Result: <br>
     * <img src="./doc-files/Cubes.png" alt="see img: Cubes.png">
     * @param points
     * @param filePath
     */
    public static void objCreateCubes(Set<Point> points, String filePath) {
        String newFilePath = createFileRawInput(filePath);
        writeFileCubes(points, newFilePath);
    }

    public static void objCreateSquares(Set<Square> squares, String filePath) {
        String newFilePath = createFileRawInput(filePath);
        writeFileSquares(squares, newFilePath);
    }

    /**
     * This method determines the name for and creates an output file. It will use the name of the input file,
     * the ending .obj and will overwrite any existing files with that name
     * @param filePath the path of the input file
     * @return the path of the output file
     */
    private static String createFileRawInput(String filePath) {
        System.out.println(filePath);
        filePath = filePath.split("\\.")[0];
        String newFilePath;
        newFilePath = filePath + ".obj";
        try {
            File file = new File(newFilePath);

            //Files überschreiben:
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();

           /* Files nicht Überschreiben:
            int x = 0;
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
        System.out.println(newFilePath);
        return newFilePath;
    }

    /**
     * This method determines the name for and creates an output file. It will use the name passed to it
     * the ending .obj and will overwrite any existing files with that name
     * @param filePath the name (and directory) the file should have without any file endings
     * @return the path of the output file
     */
    private static String createFileFixedInput(String filePath) {
        filePath = filePath + ".obj";
        try {
            File file = new File(filePath);

            //Files überschreiben:
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();

           /* Files nicht Überschreiben:
            int x = 0;
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
        return filePath;
    }

    /**
     * Subroutine of {@link #objCreateTriangles(List, String)}
     * This Method writes the contents of the file, overwriting any existing content
     *
     * @param triangles
     * @param filePath
     */
    private static void writeFileTriangles(List<Triangle> triangles, String filePath) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("o ").append(filePath).append("\n");

            int i = 1;
            for (Triangle t : triangles) {
                builder.append(String.format("v %1$s %2$s %3$s\n", t.a.x, t.a.y, t.a.z))
                        .append(String.format("v %1$s %2$s %3$s\n", t.b.x, t.b.y, t.b.z))
                        .append(String.format("v %1$s %2$s %3$s\n", t.c.x, t.c.y, t.c.z))
                        .append(String.format("f %1$s %2$s %3$s\n", i, (i + 1), (i + 2)));
                i = i + 3;
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

    /**
     * Subroutine of {@link #objCreateTetrahedrons(Set, String)}.
     * This Method writes the contents of the file, overwriting any existing content
     * @param points
     * @param filePath
     */
    private static void writeFileTetrahedrons(Set<Point> points, String filePath) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("o ").append(filePath).append("\n");
            double offset = 0.5;
            int i = 1;
            for (Point p : points) {
                builder.append(String.format("v %1$s %2$s %3$s\n", p.x + offset, p.y - offset, p.z + offset))
                        .append(String.format("v %1$s %2$s %3$s\n", p.x - offset, p.y - offset, p.z + offset))
                        .append(String.format("v %1$s %2$s %3$s\n", p.x, p.y - offset, p.z - offset))
                        .append(String.format("v %1$s %2$s %3$s\n", p.x, p.y + offset, p.z))
                        .append(String.format("f %1$s %2$s %3$s\n", i, (i + 1), (i + 2)))
                        .append(String.format("f %1$s %2$s %3$s\n", i, (i + 1), (i + 3)))
                        .append(String.format("f %1$s %2$s %3$s\n", i, (i + 2), (i + 3)))
                        .append(String.format("f %1$s %2$s %3$s\n", i + 1, (i + 2), (i + 3)));
                i = i + 4;
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

    /**
     * Subroutine of {@link #objCreateCubes(Set, String)}
     * This Method writes the contents of the file, overwriting any existing content
     * @param points
     * @param filePath
     */
    private static void writeFileCubes(Set<Point> points, String filePath) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("o ").append(filePath).append("\n");
            double offset = 0.5;
            int i = 1;
            for (Point p : points) {


                double[] v0Int = new double[]{p.x + offset, p.y + offset, p.z + offset};
                String[] v0 = Arrays.stream(v0Int).mapToObj(rounder::roundToString).toArray(String[]::new);//0 +++

                double[] v1Int = new double[]{p.x + offset, p.y + offset, p.z - offset};
                String[] v1 = Arrays.stream(v1Int).mapToObj(rounder::roundToString).toArray(String[]::new);//1 ++-

                double[] v2Int = new double[]{p.x + offset, p.y - offset, p.z + offset};
                String[] v2 = Arrays.stream(v2Int).mapToObj(rounder::roundToString).toArray(String[]::new);//2 +-+

                double[] v3Int = new double[]{p.x - offset, p.y + offset, p.z + offset};
                String[] v3 = Arrays.stream(v3Int).mapToObj(rounder::roundToString).toArray(String[]::new);//3 -++

                double[] v4Int = new double[]{p.x + offset, p.y - offset, p.z - offset};
                String[] v4 = Arrays.stream(v4Int).mapToObj(rounder::roundToString).toArray(String[]::new);//4 +--

                double[] v5Int = new double[]{p.x - offset, p.y - offset, p.z + offset};
                String[] v5 = Arrays.stream(v5Int).mapToObj(rounder::roundToString).toArray(String[]::new);//5 --+

                double[] v6Int = new double[]{p.x - offset, p.y + offset, p.z - offset};
                String[] v6 = Arrays.stream(v6Int).mapToObj(rounder::roundToString).toArray(String[]::new);//6 -+-

                double[] v7Int = new double[]{p.x - offset, p.y - offset, p.z - offset};
                String[] v7 = Arrays.stream(v7Int).mapToObj(rounder::roundToString).toArray(String[]::new);//7 ---

                builder.append(String.format("v %1$s %2$s %3$s\n", v0[0], v0[1], v0[2]))//0 +++
                        .append(String.format("v %1$s %2$s %3$s\n", v1[0], v1[1], v1[2]))//1 ++-
                        .append(String.format("v %1$s %2$s %3$s\n", v2[0], v2[1], v2[2]))//2 +-+
                        .append(String.format("v %1$s %2$s %3$s\n", v3[0], v3[1], v3[2]))//3 -++
                        .append(String.format("v %1$s %2$s %3$s\n", v4[0], v4[1], v4[2]))//4 +--
                        .append(String.format("v %1$s %2$s %3$s\n", v5[0], v5[1], v5[2]))//5 --+
                        .append(String.format("v %1$s %2$s %3$s\n", v6[0], v6[1], v6[2]))//6 -+-
                        .append(String.format("v %1$s %2$s %3$s\n", v7[0], v7[1], v7[2]))//7 ---
                        .append(String.format("f %1$s %2$s %3$s %4$s\n", i + 0, i + 3, i + 5, i + 2))
                        .append(String.format("f %1$s %2$s %3$s %4$s\n", i + 3, i + 6, i + 7, i + 5))
                        .append(String.format("f %1$s %2$s %3$s %4$s\n", i + 6, i + 1, i + 4, i + 7))
                        .append(String.format("f %1$s %2$s %3$s %4$s\n", i + 1, i + 0, i + 2, i + 4))
                        .append(String.format("f %1$s %2$s %3$s %4$s\n", i + 0, i + 3, i + 6, i + 1))
                        .append(String.format("f %1$s %2$s %3$s %4$s\n", i + 2, i + 5, i + 7, i + 4));
                i = i + 8;
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

    private static void writeFileSquares(Set<Square> squares, String filePath) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("o ").append(filePath).append("\n");
            double offset = 0.5;
            int i = 1;
            for (Square s : squares) {
                Point a = s.getA();
                Point b = s.getB();
                Point c = s.getC();
                Point d = s.getD();

                builder.append(String.format("v %1$s %2$s %3$s\n", a.x, a.y, a.z))
                        .append(String.format("v %1$s %2$s %3$s\n",b.x, b.y, b.z))
                        .append(String.format("v %1$s %2$s %3$s\n",c.x, c.y, c.z))
                        .append(String.format("v %1$s %2$s %3$s\n",d.x, d.y, d.z))
                        .append(String.format("f %1$s %2$s %3$s %4$s\n", i, i + 1, i + 2, i + 3));
                i = i + 4;
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

    /*

    public static void objCreateSingle(double[][] d, String filePath) {
        String newFilePath = createFileRawInput(filePath);
        writeFileSingle(d, newFilePath);
    }

    private static void writeFileSingle(double[][] d, String filePath) {
        try {
            FileWriter myWriter = new FileWriter(filePath, false);
            double midpoint = d[0][0] + d[0][1] + d[1][0] + d[1][1];
            midpoint = midpoint / 4;
            myWriter.write("o " + filePath + "\n" +
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
    */

    public static Rounder getRounder() {
        return rounder;
    }

    public static void setRounder(Rounder rounder) {
        ObjConverter.rounder = rounder;
    }
}
