package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UniquePathsII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 63. Unique Paths II
 */
public class UniquePathsII {
    /**
     * Follow up for "Unique Paths":

     Now consider if some obstacles are added to the grids.
How many unique paths would there be?

     An obstacle and empty space is marked as 1 and 0 respectively 
     in the grid.

     For example,
     There is one obstacle in the middle of a 3x3 grid as 
     illustrated below.

     [
     [0,0,0],
     [0,1,0],
     [0,0,0]
     ]
     The total number of unique paths is 2.

     time : O(m * n)
     space : O(n)

     * @param obstacleGrid
     * @return
     */

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int length = obstacleGrid[0].length;
        int[] res = new int[length];
        res[0] = 1;
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    res[j] = 0;
                } else if (j > 0) {
                    res[j] += res[j - 1];
                }
            }
        }
        return res[length - 1];
    }
    
    //easy to understand version, 
    // thinking process:
    //so from top left to right bottom, typical back tracking, 
    // so i j is controlled by i-1, j and i j -1 so we want to 
    // have dp foruma to carry on
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int rowLen = obstacleGrid.length;
        int colLen = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[rowLen - 1][colLen - 1] == 1)
            return 0;
        int[][] dp = new int[rowLen + 1][colLen + 1];
        dp[1][1] = 1;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i + 1][j + 1] = 0;
                    continue;
                }
                if (!(i == 0 && j == 0)) {
                    dp[i + 1][j + 1] = dp[i][j + 1] + dp[i + 1][j];
                }
            }
        }
        return dp[rowLen][colLen];
    }
    
    //back tracking
    private int[][] arr;

    public int uniquePathsWithObstacle3(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        arr = new int[m][n];
        arr[0][0] = 1;
        return help(m-1, n-1, obstacleGrid);
    }

    private int help(int m, int n, int[][] obstacleGrid){
        if(obstacleGrid[m][n] == 1){
            return 0;    
        }
        if(arr[m][n] != 0) return arr[m][n];
        int sum = 0;
        if(m > 0) sum += help(m-1, n, obstacleGrid);
        if(n > 0) sum += help(m, n-1, obstacleGrid);
        arr[m][n] = sum;
        return sum;
    }
}
