package hatecode._0001_0999;

import java.util.*;
public class _469onvexPolygon {
/*
469. Convex Polygon
Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).
[[0,0],[0,1],[1,1],[1,0]]

Answer: True
*/
    public boolean isConvex(List<List<Integer>> points) {
        // Assumptions  
        // 1. points are given in order which forms a polygon (are there duplicate points?)
        // 2. a simple polygon (A. each point only has 2 edges connected to it. B. no edges are crossing each other)
        // 3. remember to discuss how do we define result when num points <= 3 (same points? collinear?)
        Integer globalOrient = null;
        for (int i = 0; i < points.size(); i++) {
            int curOrient = getOrientation(points, i);
            if (curOrient == 0) continue;                            // if it's collinear, continue to check next point
            else if (globalOrient == null) globalOrient = curOrient; // if not collinear & global NOT set, set it
            else if (globalOrient != curOrient) return false;        // if not collinear & global set,     compare
        }
        return true;
    }
    
    // use cross product and check the orientation of points i, i+1, i+2
    // https://www.geeksforgeeks.org/orientation-3-ordered-points/
    // what's determinant: https://www.youtube.com/watch?v=Ip3X9LOh2dk
    
    //for successive 3 points, if their 斜率 slope is p1, p2, p3
    //(y2 - y1)*(x3 - x2) - (y3 - y2)*(x2 - x1)
    // > 0 clock wise
    //= 0 linear
    // < 0 counter clockwise
    private int getOrientation(List<List<Integer>> points, int i) {
        List<Integer> pA = points.get((i + 0) % points.size()); // first point
        List<Integer> pB = points.get((i + 1) % points.size()); // second point
        List<Integer> pC = points.get((i + 2) % points.size()); // third point
        int determinant = (pB.get(0) - pA.get(0)) * (pC.get(1) - pB.get(1)) - (pC.get(0) - pB.get(0)) * (pB.get(1) - pA.get(1));
        return Integer.signum(determinant);
    }
}