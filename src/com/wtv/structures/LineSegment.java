package com.wtv.structures;

import java.util.Objects;

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

    public void swap(){
        double x = p1x;
        double y = p1y;
        p1x = p2x;
        p1y = p2y;
        p2x = x;
        p2y = y;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineSegment that = (LineSegment) o;
        return (Double.compare(that.p1x, p1x) == 0 &&
                    Double.compare(that.p1y, p1y) == 0 &&
                    Double.compare(that.p2x, p2x) == 0 &&
                    Double.compare(that.p2y, p2y) == 0) ||
                (Double.compare(that.p1x, p2x) == 0 &&
                    Double.compare(that.p1y, p2y) == 0 &&
                    Double.compare(that.p2x, p1x) == 0 &&
                    Double.compare(that.p2y, p1y) == 0) ;
    }
}
