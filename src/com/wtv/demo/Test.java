package com.wtv.demo;

import com.wtv.processing.Curves;
import com.wtv.structures.Spot;

import java.util.Arrays;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        Spot[] spots = {new Spot(1,1), new Spot(2,2), new Spot(3,1)};
        Curves.getControlPoints(new LinkedList<>(Arrays.asList(spots)));
        int i;
    }
}
