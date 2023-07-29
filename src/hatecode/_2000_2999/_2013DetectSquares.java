package hatecode._2000_2999;

import java.util.*;

public class _2013DetectSquares {
/*
2013. Detect Squares
You are given a stream of points on the X-Y plane. Design an algorithm that:

Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.

Implement the DetectSquares class:

DetectSquares() Initializes the object with an empty data structure.
void add(int[] point) Adds a new point point = [x, y] to the data structure.
int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.


Input
["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
[[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
Output
[null, null, null, null, 1, 0, null, 2]

*/

/*
 * thinking process: O(1)
 * 
 * the problem is to say: implement two methods 
 * 1. add, add one point (x, y) to the class
 * 2. count(int[] p), count how many squares with point p, duplicate points can be counted 
 * 
 * use map, x<-> (y, y's count) to store all points 
 * then when count,  we 
 * 
 * 
 */

    Map<Integer, Map<Integer, Integer>> map;
    public _2013DetectSquares() {
        map = new HashMap<>();
    }
    
    public void add(int[] p) {
        map.computeIfAbsent(p[0], v->new HashMap<>()).merge(p[1], 1, Integer::sum);
    }
    
    public int count(int[] p) {
        int x = p[0], y = p[1];
        
        if (!map.containsKey(x)) return 0;
        int res = 0;
        Map<Integer, Integer> ymap = map.get(x);
        for(int col : map.keySet()) {
            Map<Integer, Integer> colmap = map.get(col);
            if (col != x) {
                int d = col - x;
                res += colmap.getOrDefault(y, 0) * ymap.getOrDefault(y+d, 0) * colmap.getOrDefault(y+d, 0);
                res += colmap.getOrDefault(y, 0) * ymap.getOrDefault(y-d, 0) * colmap.getOrDefault(y-d, 0);
            }
        }
        
        return res;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */
