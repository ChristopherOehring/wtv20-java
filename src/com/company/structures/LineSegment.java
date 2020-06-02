package com.company.structures;

public class LineSegment {
    public double p1x;
    public double p1y;
    public double p2x;
    public double p2y;

    public LineSegment(double p1x, double p1y, double p2x, double p2y) {
        this.p1x = p1x;
        this.p1y = p1y;
        this.p2x = p2x;
        this.p2y = p2y;
    }

    @Override
    public String toString() {
        return "LineSegment{" +
                "(" + p1x +
                ", " + p1y + " )" +
                ", (" + p2x +
                ", " + p2y + ") " +
                '}';
    }
}
