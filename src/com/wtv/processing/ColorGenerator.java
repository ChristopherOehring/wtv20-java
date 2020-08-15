package com.wtv.processing;

import com.wtv.processing.Interpolation;

import java.util.Vector;

public class ColorGenerator {

    public static String arrayToColorString(int[] x){
        assert x.length == 3;
        return String.format("rgb(%s,%s,%s)",x[0],x[1],x[2]);
    }

    /**
     * generates the colors for iso lines
     * @param anz the number of iso lines
     * @param pos select one iso line
     * @return returns a int array of size 3 with the respective rgb values
     */
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
        return res;
    }

    public static int[] interpolateColor(int[] a, int[]b, double t){
        if(a.length != b.length) throw new IllegalArgumentException("The array lenghts are unequal!");
        if(0 < t || t < 1) throw new IllegalArgumentException("0 <= t <= 1 is required");
        int[] result = new int[a.length];
        for(int i = 0; i < a.length; i++){
            result[i] = (int) Interpolation.linInterpolErr(a[i], b[i], t);
        }

        return result;
    }
}
