package com.wtv.structures;

public class Triangle {
    public Point a;
    public Point b;
    public Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void setA(double x, double y, double z) {
        this.a = new Point(x,y,z);
    }

    public void setB(double x, double y, double z) {
        this.b = new Point(x,y,z);
    }

    public void setC(double x, double y, double z) {
        this.c = new Point(x,y,z);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
