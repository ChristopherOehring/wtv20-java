package com.wtv.processing;

public class Interpolation {
    public static void main(String[] args) {
        compare(2,3, 2);
    }

    public static void compare(double v0, double v1, double x){
        System.out.println(String.format("Input: %s, %s, %s", v0, v1, x));
        System.out.println("Continue: " + linInterpolContinue(v0,v1,x));
        System.out.println("FixInterval: " + linInterpolFixInterval(v0,v1,x));
        System.out.println("DefZero: " + linInterpolDefZero(v0,v1,x));
        try {
            System.out.println("Err: " + linInterpolErr(v0,v1,x));
        } catch (IllegalArgumentException e){
            System.out.println("Err: ");
            e.printStackTrace();
        }
    }

    public static double linInterpolContinue(double v0, double v1, double x){
        return ((v1 - v0) * x) + v0;
    }

    public static double linInterpolFixInterval(double v0, double v1, double x){
        if(x < 0) return v0;
        if(x > 1) return v1;
        return ((v1 - v0) * x) + v0;
    }

    public static double linInterpolDefZero(double v0, double v1, double x) {
        if (0 > x || x > 1) return 0;
        return ((v1 - v0) * x) + v0;
    }

    public static double linInterpolErr(double v0, double v1, double x) {
        if (0 > x || x > 1) throw new IllegalArgumentException();
        return ((v1 - v0) * x) + v0;
    }
}
