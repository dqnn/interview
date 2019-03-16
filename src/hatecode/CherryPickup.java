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
    public int cherryPickup(int[][] g) {
        if (g == null || g.length < 1 || g[0].length < 1) return 0;
        //since N x N matrix
        int r = g.length;
        
        int[][][] dp = new int[r + 1][r + 1][r + 1];
        for(int[][] d : dp) {
            for(int[] e : d) Arrays.fill(e, Integer.MIN_VALUE);
        }
        
        dp[1][1][1] = g[0][0];
        for(int x1 =1; x1 <=r; x1++) {
            for(int y1 =1; y1 <=r; y1++) {
                for(int x2= 1; x2<=r;x2++) {
                    int y2= x1 + y1 -x2;
                    if (dp[x1][y1][x2] > 0 || y2 < 1 || y2 > r || g[x1-1][y1-1] == -1 || g[x2-1][y2-1] == -1) {
                        continue;
                    }
                    int cur = Math.max(Math.max(dp[x1 - 1][y1][x2], dp[x1 - 1][y1][x2 - 1]), Math.max(dp[x1][y1 - 1][x2], dp[x1][y1 - 1][x2 - 1]));
                    if (cur < 0) continue;
                    
                    dp[x1][y1][x2] = cur + g[x1-1][y1-1];
                    if (x1 != x2) dp[x1][y1][x2] += g[x2 - 1][y2 -1];
                }
            }
        }
        return Math.max(dp[r][r][r], 0);
        
        
    }
}