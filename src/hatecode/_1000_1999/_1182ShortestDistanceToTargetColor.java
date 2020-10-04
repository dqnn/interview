package hatecode._1000_1999;

import java.util.*;
public class _1182ShortestDistanceToTargetColor {
/*
1182. Shortest Distance to Target Color
You are given an array colors, in which there are three colors: 1, 2 and 3.

You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.

 

Example 1:

Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
Output: [3,0,3]
*/
  
    //thinking process: O(n)/O(n)
    
    //the problem is to say: given colors, 1,2 3 then we have list of queries, one query
    //queries[i][0] is the index in colors, queries[0][1] is the target color, 
    //for queries[i][0] return the shortest path from colors[i] to the target
    //[1,1,2,1,3,2,2,3,3] queries = [[1,3],[2,2],[6,1]]
    //for [1,3] means colors[1] = 1, shortest 3 is colors[4] so is 4-1 = 3
    
    //ld[i][j] means for colors[i] the shortest distance to colors[j] from left
    //rd[i][j] means for colors[i] the shortest distance to colors[j] from right
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int n = colors.length;
        int[][] ld = new int[4][n];
        int[][] rd = new int[4][n];
        for (int i = 1; i < 4; i++) {
            Arrays.fill(ld[i], -1);
            Arrays.fill(rd[i], -1);
        }
        computeLft(colors, ld);
        computeRgt(colors, rd);
        List<Integer> res = new ArrayList<>();
        for (int[] q : queries) {
            int i = q[0];
            int c = q[1];
            int l = ld[c][i];
            int r = rd[c][i];
            //just choose the minor one
            res.add(l == -1 || r == -1 ? Math.max(l, r) : Math.min(l, r));
        }
        return res;
    }

    //from left count
    private void computeLft(int[] colors, int[][] res) {
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < colors.length; j++) {
                if (colors[j] == i) {
                    res[i][j] = 0;
                } else if (j > 0 && res[i][j - 1] != -1) {
                    res[i][j] = res[i][j - 1] + 1;
                }
            }
        }
    }
    
    //from right
    private void computeRgt(int[] colors, int[][] res) {
        for (int i = 1; i < 4; i++) {
            for (int j = colors.length - 1; j >= 0; j--) {
                if (colors[j] == i) {
                    res[i][j] = 0;
                } else if (j < colors.length - 1 && res[i][j + 1] != -1) {
                    res[i][j] = res[i][j + 1] + 1;
                }
            }
        }
    }
    
    //binary search 
    
    public List<Integer> shortestDistanceColor_BinarySearch(int[] colors, int[][] queries) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < colors.length; i++) {
            int c = colors[i];
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }
        List<Integer> ans = new ArrayList<>();
        for (int[] query : queries) {
            int index = query[0];
            int c = query[1];
            if (!map.containsKey(c)) {
                ans.add(-1);
            } else {
                ans.add(binarySearch(index, map.get(c)));
            }
        }
        return ans;
    }
    
    public int binarySearch(int index, List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < index) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int res = left;
        if (left - 1 >= 0 && index - list.get(left - 1) < list.get(left) - index) {
            res = left - 1;
        }
        return Math.abs(list.get(res) - index);
    }
    
}