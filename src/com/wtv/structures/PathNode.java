package com.wtv.structures;

import java.util.Set;

/**
 * This was used for the original path implementation, but it created all sorts of issues.
 * Interestingly the recursive Methods never where an issue.
 */
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
