package com.company.converter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader
{

    public static void main(String[] args) // Eingabe: Filepath, Spaltentrenner(ohne space)
    {
        try {
            double[][][] result = dateiLesen3D("examples/sphere01.csv");
            int lenght = result.length;
            for (int a = 0; a < lenght; a++){
                System.out.println("");
                for (int b = 0; b < lenght; b++){
                    System.out.println("");
                    for (int c = 0; c < lenght; c++)
                    {
                        System.out.print(result[a][b][c] + ", \t");
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /*public static void dateiLesen(String name) throws IOException
    {
        FileReader fr = new FileReader(name);
        BufferedReader br = new BufferedReader(fr);

        String zeile = null;

        double[][] list = new double[2][2]; //list[i][j]
        int i = 0;
        int j = 0;
        while ((zeile = br.readLine()) != null) {
            String[] s = zeile.split(", ");

            for (int x = 0; x < s.length; x++) {
                list[i][x] = Double.parseDouble(s[x]);
            }

            i++;
        }
        System.out.println(Arrays.deepToString(list));
        br.close();

    }*/

    public static double[][] dateiLesen2D(String name, String spaltentrenner) throws IOException
    {
        FileReader fr = new FileReader(name);
        BufferedReader br = new BufferedReader(fr);

        String zeileStr;

        // Matrix größe bestimmen
        int a = 0;
        int b = Integer.MAX_VALUE;
        int i = 1;
        boolean mErr = false;
        while ((zeileStr = br.readLine()) != null)
        {
            zeileStr = zeileStr.replaceAll("\\s","");
            String[] s = zeileStr.split(spaltentrenner);
            if (s[0].equals("")) continue;
            a++;
            if(s.length < b){
                b = s.length;
                if(a != 1) {
                    mErr = true;
                    System.out.println("line " + i + " too short");
                    System.out.println(b + " " + Arrays.toString(s));

                }
            }
            if(s.length > b){
                mErr = true;
                System.out.println("line " + i + " too long");
            }
            i++;
        }
        if (mErr) System.out.println("Error: Matrix is incorrect and will be trimmed");

        double[][] list = new double[a][b]; //list[i][j]

        // Filereader neustarten
        fr = new FileReader(name);
        br = new BufferedReader(fr);

        // Daten einlesen
        i = 0;
        while ((zeileStr = br.readLine()) != null)
        {
            //System.out.println(i);

            zeileStr = zeileStr.replaceAll("\\s","");
            String[] zeileStrArr = zeileStr.split(spaltentrenner);
            if (zeileStrArr[0].equals("")) continue;
            for (int x = 0; x < b; x++) {
                 try{
                     //System.out.println("  " + x );

                     list[i][x] = Double.parseDouble(zeileStrArr[x]);
                 } catch (Exception e){
                     e.printStackTrace();
                     System.out.println("Möglicherweise ein falscher Spaltentrenner (Default = \",\")");
                 }
            }
            i++;
        }
        br.close();
        return list;
    }


    //TODO: this assumes the area of input data is a cube maybe this needs to be fixed
    public static double[][][] dateiLesen3D(String name) throws IOException
    {

        List<String> fileList = new ArrayList<>();

        /*
         * einlesen der datei in "file".
         * Dabei werden automatisch alle Leerzeilen entfernt.
         * erlaubt außerdem kommentare mit #
         */
        FileReader fr = new FileReader(name);
        BufferedReader br = new BufferedReader(fr);
        String zeileStr = null;
        zeileStr = br.readLine();
        while (zeileStr != null) {
            zeileStr = zeileStr.replaceAll("\t", "").trim();
            if(!zeileStr.equals("") && !(zeileStr.charAt(0) == '#')) {
                fileList.add(zeileStr);
            }
            zeileStr = br.readLine();
        }
        br.close();
        Iterator<String> file = fileList.iterator();

        double[][][] result; // result[x][y][z]
        int i = 0;
        int lenght;
        //find the number of rows
        zeileStr = file.next().trim().replaceAll(" +", " ");
        String[] lineArray = zeileStr.split(" ");
        if(lineArray[0].equals("dataRowsPerSlice") && lineArray.length == 2) {
            try {
                lenght = Integer.parseInt(lineArray[1]);
            } catch (NumberFormatException e){
                e.printStackTrace();
                System.out.println("Error: expected number of rows but found: \"" + lineArray[1] + "\"");
                return null;
            }
        } else {
            System.out.println("Error: Syntax error at line " + i);
            return null;
        }

        //write the array
        result = new double[lenght][lenght][lenght];
        String line = "";
        for(int z = 0; z < lenght; z++){
            for(int y = 0; y < lenght; y++) {
                line = file.next();
                String[] numbersAsString = line.split(", +");

                double[] numbers;

                try{
                    numbers = Arrays.stream(numbersAsString)
                            .mapToDouble(s -> Double.parseDouble(s))
                            .toArray();
                } catch (NumberFormatException e) {
                    System.out.println("Error: at line: " + line);
                    return null;
                }
                result[y][z] = numbers;
            }
        }
        return result;
    }

    /*public static void dateiLesen(String name){
        File file = new File(name);
        System.out.println("'" + name + "'" + " kann gelesen werden: " + file.canRead());
        if (!file.canRead() || !file.isFile())
            System.exit(0);

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(name));
            String zeile = null;

            while ((zeile = in.readLine()) != null) {
                System.out.println("Gelesene Zeile: " + zeile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        }
    }*/
}
