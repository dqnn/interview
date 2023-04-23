package _2000_2999;

import java.util.*;

public class _2250CountNumberOfRectanglesContainingEachPoint {
    /*
    2250. Count Number of Rectangles Containing Each Point
    
    You are given a 2D integer array rectangles where rectangles[i] = [li, hi] indicates that ith rectangle has a length of li and a height of hi. You are also given a 2D integer array points where points[j] = [xj, yj] is a point with coordinates (xj, yj).
    
    The ith rectangle has its bottom-left corner point at the coordinates (0, 0) and its top-right corner point at (li, hi).
    
    Return an integer array count of length points.length where count[j] is the number of rectangles that contain the jth point.
    
    The ith rectangle contains the jth point if 0 <= xj <= li and 0 <= yj <= hi. Note that points that lie on the edges of a rectangle are also considered to be contained by that rectangle.
    
    Example 1:
    
    
    Input: rectangles = [[1,2],[2,3],[2,5]], points = [[2,1],[1,4]]
    Output: [2,1]
    */

    /*
     * 
     */
        public int[] countRectangles(int[][] A, int[][] p) {
            int n = A.length;
            Map<Integer, List<Integer>> map = new HashMap<>();
            for(int[] a: A) {
                map.computeIfAbsent(a[1], v->new ArrayList<>()).add(a[0]);
            }
            
            for(int k : map.keySet()) {
                Collections.sort(map.get(k));
            }
            
            int[] res = new int[p.length];
            for(int i = 0; i<p.length; i++) {
                for(int j = p[i][1]; j <= 100; j++) {
                    if (map.containsKey(j)) {
                        res[i] += map.get(j).size() - find(p[i][0], map.get(j));
                    }
                }
            }
            
            return res;
        }
        
        
        private int find(int x, List<Integer> A) {
            int l = 0, r = A.size() - 1;
            
            while(l < r) {
                int m = l + (r - l)/2;
                if (A.get(m) >=x)  r = m;
                else l = m + 1; 
            }
            
            if (A.get(l) < x) return l+ 1;
            else return l;
        }
    }