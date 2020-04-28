package com.company;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CSVReader
{

    public static void main(String[] args) throws IOException // Eingabe: Filepath, Spaltentrenner(ohne space)
    {
	    // write your code here
        // dateiLesen("/home/chris/IdeaProjects/WTV/src/com/company/test1");
        double[][] d;
        try{
            if(args.length == 2)    d = dateiLesenDyn(args[0], args[1]);
            else                    d = dateiLesenDyn(args[0], ",");
        } catch (FileNotFoundException | IndexOutOfBoundsException e) {
            System.out.println("Reverting to Test File");
            d = dateiLesenDyn("/home/chris/IdeaProjects/WTV/src/com/company/test1", ",");
        }
        System.out.println(Arrays.deepToString(d));
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

    public static double[][] dateiLesenDyn(String name, String spaltentrenner) throws IOException
    {
        FileReader fr = new FileReader(name);
        BufferedReader br = new BufferedReader(fr);

        String zeileStr = null;

        // Matrix größe bestimmen
        int a = 0;
        int b = Integer.MAX_VALUE;
        boolean mErr = false;
        while ((zeileStr = br.readLine()) != null)
        {
            zeileStr.replaceAll("\\s","");
            a++;
            String[] s = zeileStr.split(spaltentrenner);
            if(s.length < b){
                b = s.length;
                mErr = true;
            }
            if(s.length > b){
                mErr = true;
            }
            if(a == 1) mErr = false;
        }
        if (mErr) System.out.println("Error: Matrix is incorrect and will be trimmed");

        double[][] list = new double[a][b]; //list[i][j]

        // Filereader neustarten
        fr = new FileReader(name);
        br = new BufferedReader(fr);

        // Daten einlesen
        int i = 0;
        while ((zeileStr = br.readLine()) != null)
        {
            zeileStr.replaceAll("\\s","");
            String[] zeileStrArr = zeileStr.split(spaltentrenner);
            for (int x = 0; x < b; x++) {
                 try{
                     list[i][x] = Double.parseDouble(zeileStrArr[x]);
                 } catch (Exception e){
                     e.printStackTrace();
                     System.out.println("Möglicerweise ein falscher Spaltentrenner (Default = \",\"");
                 }
            }
            i++;
        }
        br.close();
        return list;
    }

    private static void print(){

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
