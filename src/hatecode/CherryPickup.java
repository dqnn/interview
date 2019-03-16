package hatecode;
import java.util.*;
public class CherryPickup {
/*
741. Cherry Pickup
In a N x N grid representing a field of cherries, each cell is one of three possible integers.

 

0 means the cell is empty, so you can pass through;
1 means the cell contains a cherry, that you can pick up and pass through;
-1 means the cell contains a thorn that blocks your way.

Example 1:

Input: grid =
[[0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]]
Output: 5
 
*/
    //interview friendly, 
    //thinking process: given a matrix, 0 empty, 1 cherry, -1 obstack, so you walk from 0,0->n-1,n-1, then back
    //try to grab more cherries as many as possible. 
    //so if using two dp will be wrong because when you back, you may not be able to catch all cherries, 
    //so we think about two people start at the same time from top left to botton right, both only down or right
    
    //we have the formula: x1 + y1 = x2 + y2, so we can only use 3 dimension to simplify the DP
    //another part is the how to initialize the dp and how to get from internal loop
    public int cherryPickup(int[][] g) {
        if (g == null || g.length < 1 || g[0].length < 1) return 0;
        //since N x N matrix
        int r = g.length;
        
        int[][][] dp = new int[r + 1][r + 1][r + 1];
        for(int[][] d : dp) {
            for(int[] e : d) Arrays.fill(e, Integer.MIN_VALUE);
        }
        //we initialize the 1 1 1 same as 0 0 0 if length is n
        dp[1][1][1] = g[0][0];
        //DP computing is also key, we scan row by row, and we use last result to update next ones
        for(int x1 =1; x1 <=r; x1++) {
            for(int y1 =1; y1 <=r; y1++) {
                for(int x2= 1; x2<=r;x2++) {
                    int y2= x1 + y1 -x2;
                    if (dp[x1][y1][x2] > 0 || y2 < 1 || y2 > r || g[x1-1][y1-1] == -1 || g[x2-1][y2-1] == -1) {
                        continue;
                    }
                    //for point x1,y1,x2,y2, there are two points, means 1 go to x1,y1, 2 go to x2,y2
                    //they are independent, just need to check x1 == x2, since x1 + y1 = x2 + y2, so 
                    //x1,y1 has 2 ways, for x2,y2, it also have 2 ways, but if we fix x1 and y1, then for 2nd
                    //point, there will be only 1 factor, x2-1, x2
                    int cur = Math.max(Math.max(dp[x1 - 1][y1][x2], dp[x1 - 1][y1][x2 - 1]), 
                                       Math.max(dp[x1][y1 - 1][x2], dp[x1][y1 - 1][x2 - 1]));
                    if (cur < 0) continue;
                    
                    dp[x1][y1][x2] = cur + g[x1-1][y1-1];
                    if (x1 != x2) dp[x1][y1][x2] += g[x2 - 1][y2 -1];
                }
            }
        }
        return Math.max(dp[r][r][r], 0);
        
        
    }
}