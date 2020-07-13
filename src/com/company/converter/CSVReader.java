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


    public static double[][][] dateiLesen3D(String name) throws IOException
    {

        List<String> fileList = new ArrayList<>();

        /*
         * einlesen der datei in "file".
         * Dabei werden automatisch alle Leerzeilen und whitespaces entfernt
         * erlaubt außerdem kommentare mit #
         */
        FileReader fr = new FileReader(name);
        BufferedReader br = new BufferedReader(fr);
        String zeileStr = br.readLine();
        while (zeileStr != null) {
            zeileStr = zeileStr.replaceAll("\\s+", "").trim();
            if(!zeileStr.equals("") && !(zeileStr.charAt(0) == '#')) {
                fileList.add(zeileStr);
            }
            zeileStr = br.readLine();
        }
        br.close();
        Iterator<String> file = fileList.iterator();
        //TODO alles hiervor ist nicht sinnvoll

        double[][][] result; // result[x][y][z]
        int i = 0;
        int dataRowsPerSlice;
        //find the number of rows
        zeileStr = file.next();
        if(zeileStr.contains("dataRowsPerSlice")) {
            zeileStr = zeileStr.replaceFirst("dataRowsPerSlice", "");
            try {
                dataRowsPerSlice = Integer.parseInt(zeileStr);
            } catch (NumberFormatException e){
                e.printStackTrace();
                System.out.println("Error: expected number of rows but found: \"" + zeileStr + "\"");
                return null;
            }
        } else {
            System.out.println("Error: Syntax error at line " + i);
            return null;
        }

        //write the array
        int xLength = fileList.get(1).split(",").length;
        int yLength = dataRowsPerSlice;
        int zLength = (fileList.size()-1)/dataRowsPerSlice;
        result = new double[xLength][yLength][zLength]; //error for dataRowsPerSlice=0
        for(int z = 0; z < zLength; z++){
            for(int y = 0; y < yLength; y++) {
                String line = file.next();
                String[] numbersAsString = line.split(",");
                for(int x = 0; x < yLength; x++) {
                    result[x][dataRowsPerSlice-1-y][z] = Double.parseDouble(numbersAsString[x]);
                }
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
