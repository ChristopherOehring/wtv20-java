package com.wtv.processing;

import com.wtv.structures.Pair;
import com.wtv.structures.Spot;

import java.util.LinkedList;

public class Curves {

    public static LinkedList<Spot> getControlPoints(LinkedList<Spot> oldPath) {
        LinkedList<Spot> ret = new LinkedList<>();
        boolean closed = oldPath.getFirst().equals(oldPath.getLast());

        if(oldPath.size() <= 2) {
            return oldPath;
        }
        Spot loc2;
        ret.addLast(oldPath.get(0));
        if(!closed) {
            Spot loc1;
            loc1 = new Spot(oldPath.get(1));
            loc2 = new Spot(oldPath.get(0));
            loc2.setX(2 * loc2.getX() - loc1.getX());
            loc2.setY(2 * loc2.getY() - loc1.getY());
        } else {
            loc2 = oldPath.get(oldPath.size()-2);
        }
        Pair<Spot,Spot> result = getControlPointsForPoints(
                loc2,
                oldPath.get(0),
                oldPath.get(1),
                oldPath.get(2));

        ret.addLast(result.getFirst());
        ret.addLast(result.getSecond());
        ret.addLast(oldPath.get(1));
        for(int s = 0; s < oldPath.size()-3; s++){
            result = getControlPointsForPoints(
                    oldPath.get(s),
                    oldPath.get(s+1),
                    oldPath.get(s+2),
                    oldPath.get(s+3));

            ret.addLast(result.getFirst());
            ret.addLast(result.getSecond());
            ret.addLast(oldPath.get(s+2));
        }
        if(!closed) {
            Spot loc1 = new Spot(oldPath.get(oldPath.size() - 2));
            loc2 = new Spot(oldPath.get(oldPath.size() - 1));
            loc2.setX(2 * loc2.getX() - loc1.getX());
            loc2.setY(2 * loc2.getY() - loc1.getY());
        } else {
            loc2 = oldPath.get(1);
        }
        result = getControlPointsForPoints(
                oldPath.get(oldPath.size()-3),
                oldPath.get(oldPath.size()-2),
                oldPath.get(oldPath.size()-1),
                loc2);

        ret.addLast(result.getFirst());
        ret.addLast(result.getSecond());
        ret.addLast(oldPath.get(oldPath.size()-1));

        return ret;
    }

    public static Pair<Spot, Spot> getControlPointsForPoints(Spot p0, Spot p1, Spot p2, Spot p3) {

        double ft = 0.25;

        double d01 = d(p0,p1);
        double d12 = d(p1,p2);
        double d23 = d(p2,p3);

        double tax = (p2.getX()-p1.getX())/d12+(p1.getX()-p0.getX())/d01;
        double tay = (p2.getY()-p1.getY())/d12+(p1.getY()-p0.getY())/d01;
        double tbx = (p1.getX()-p2.getX())/d12+(p2.getX()-p3.getX())/d23;
        double tby = (p1.getY()-p2.getY())/d12+(p2.getY()-p3.getY())/d23;
        double xa = p1.getX() + ft*d12*tax;
        double ya = p1.getY() + ft*d12*tay;
        double xb = p2.getX() + ft*d12*tbx;
        double yb = p2.getY() + ft*d12*tby;

        return new Pair<>(new Spot(xa, ya), new Spot(xb, yb));
    }

    private static double d(Spot a, Spot b){
        return hypot(b.getX()-a.getX(), b.getY()-a.getY());
    }

    private static double hypot(double dx, double dy) {
        return Math.sqrt(dx*dx+dy*dy);
    }
}

