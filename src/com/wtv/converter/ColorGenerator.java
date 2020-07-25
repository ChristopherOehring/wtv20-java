package com.wtv.converter;

public class ColorGenerator {

    public static String arrayToColorString(int[] x){
        assert x.length == 3;
        return String.format("rgb(%s,%s,%s)",x[0],x[1],x[2]);
    }

    public static int[] lineColor(int anz, int pos){
        anz++;
        pos++;
        int[] res = new int[3];
        int max = 510;
        int intervall = max/anz;
        int x = pos*intervall;
        if(x < 255){
            res[0] = 255;
            res[1] = x;
        } else {
            x = -x + 510;
            res[0] = x;
            res[1] = 255;
        }
        res[2] = 0;
        return res;
    }

    public static String interpolateColor(int r, int g, int b, double t){
        assert 0 <= t && t <= 1;
        return null;
    }
}
