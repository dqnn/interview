package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MinimumPathSum
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 64. Minimum Path Sum
 */
public class MinimumPathSum {
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
}
