package hatecode._0001_0999;
import java.util.*;
/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MinimumPathSum
 * Date : Aug, 2018
 * Description : 64. Minimum Path Sum
 */
public class _064MinimumPathSum {
    /**
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

     time : O(m * n)
     space : O(1)

     * @param grid
     * @return
     */
    //this version is same as DP, this kind of DP is to tak care of boundary conditions, 
    //
    public int minPathSum(int[][] g) {
        if (g == null || g.length < 1) {
            return 0;
        }
        int row = g.length, column = g[0].length;
        //scan left to right, then down
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if (i == 0 && j != 0) {
                    g[i][j] +=g[i][j-1];
                }
                if (i !=0 && j ==0) {
                    g[i][j] += g[i-1][j];
                }
                if (i != 0 && j != 0) {
                    g[i][j] += Math.min(g[i-1][j], g[i][j-1]);
                }
            }
        }
        
        return g[row - 1][column - 1];
    }
    
    //DP solution same templates with UniquePath 1 and Unique Path 2, 
    //the key is forula and initialization
    public int minPathSum_DP(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0;
        
        int r = grid.length, c = grid[0].length;
        int[][] dp = new int[r][c];
        dp[0][0] = grid[0][0];
        
        for(int i = 1; i < c; i++) dp[0][i] = dp[0][i-1] + grid[0][i]; 
        for(int i = 1; i < r; i++) dp[i][0] = dp[i-1][0] + grid[i][0]; 
        
        for(int i = 1; i < r; i++) {
            for(int j = 1; j < c; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        for(int[] t : dp) System.out.println(Arrays.toString(t));
        return dp[r-1][c-1];
    }
}
