package com.company.structures;

import java.util.Set;

public class PathNode {
    final double x;
    final double y;
    Set<PathNode> following;

    public Set<PathNode> getFollowing() {
        return following;
    }

    public void setFollowing(Set<PathNode> following) {
        this.following = following;
    }

    public PathNode(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
