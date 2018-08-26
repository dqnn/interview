package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaxPointsonaLine
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 149. Max Points on a Line
 */
public class MaxPointsonaLine {
    /**
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6

     1，在x轴
     2，相同点
     3，精度问题(GCD) 1/3 1/3

     * time : O(n^2);
     * space : O(n);
     * @param points
     * @return
     */
   
 // interview friendly
    // we want to know how many points are on same line, so we want to use one point to connect all other points, when 
    //we connect, we want to know from history whether there are some points are on the same line
    // so we use a map for only second loop to store how many have same slope, but we use y/gcd / x/gcd as slope also as
    // a key in the map so when we process next point, we will compare them with same slope since p[i] never change 
    //when scan, so it will only on the same line
    public int maxPoints(Point[] p) {
        if (p == null || p.length < 1) {
            return 0;
        }
        
        if (p.length < 3) {
            return p.length;
        }
        int res = 0;
        for(int i = 0; i < p.length; i++) {
            int samePoint = 0, sameXAxis = 1;
            Map<String, Integer> map = new HashMap<>();
            for(int j = 0; j< p.length; j++) {
                if (i == j) {
                    continue;
                }
                if (p[i].x == p[j].x && p[i].y == p[j].y) {
                    // this only count the duplicate ones, cannot inlude p[i]
                    samePoint++;
                }
             // this has to be special hanling or we cannot use x /gcd, since gcd cannot be 0
                if (p[i].x == p[j].x) {
                    sameXAxis ++;
                    continue;
                }
                
                int x = p[i].x - p[j].x;
                int y = p[i].y - p[j].y;
                int gcd = gcd(x, y);
                // this is better why:
                // 7/3 is bad because it has floating number so it may has some dizzy faults. and 
                // int will ignore some 4/3 numbers after .
                String key = (x / gcd) + "/" + (y /gcd);
                // because for first time, p[i] has to be in the array
                map.put(key, map.getOrDefault(key, 1) + 1);
                // 
                res = Math.max(res, map.get(key) + samePoint);
            }
            res = Math.max(res, sameXAxis);
        }
        
        return res;
    }
    // greatest common driver 最大公约数
    public int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}
