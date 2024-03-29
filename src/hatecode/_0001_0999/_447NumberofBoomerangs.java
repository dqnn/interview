package hatecode._0001_0999;

import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NumberofBoomerangs
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 447. Number of Boomerangs
 */
public class _447NumberofBoomerangs {
    /**
     * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k)
     * such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

     Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of
     points are all in the range [-10000, 10000] (inclusive).

     Example:
     Input:
     [[0,0],[1,0],[2,0]]

     Output:
     2

     Explanation:
     The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

     time : O(n^2)
     space : O(n)


     * @param points
     * @return
     */

    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int distance = getDistance(points[i], points[j]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
            // combnations, so the problem requires how many to combinations in 
            // the result set
            for (int val : map.values()) {
                res += val * (val - 1);
            }
            //we clear the map because for p[i]  we have done searching 
            //all points to p[i], if nexr cycle we have same distance then 
            //result will be polluted
            map.clear();
        }
        return res;
    }

    public int getDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }

}
