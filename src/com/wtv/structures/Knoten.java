package com.wtv.structures;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Knoten {
    Set<LinkedList<Spot>> paths = new HashSet<>();
    Spot spot;

    public Knoten(Spot spot) {
        this.spot = spot;
    }

    public Knoten(Set<LinkedList<Spot>> paths, Spot spot) {
        this.paths = paths;
        this.spot = spot;
    }

    public void add(LinkedList<Spot> path) {
        paths.add(path);
    }

    public Set<LinkedList<Spot>> getPaths() {
        return paths;
    }

    public Spot getSpot() {
        return spot;
    }
}
