package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UniquePathsII
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 63. Unique Paths II
 */
public class _063UniquePathsII {
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

Follow up:

Round 2
1. 给定一个二维数组，从(0,0)出发到第一行最后一个位置，每次只能向右，
向右上或右下(对角线)走一格，问有多少种走法
2. Follow up: 如果给你一些点，要求你必须经过这些点，有多少种走法
3. Follow up: 如果指定一个高度(row number)，你的路径必须经过至少一次这个高度，有多少种走法
     * @param m
     * @return
     */
    /* thinking process: O(mn)
     * 
     */
    public int uniquePathsWithObstacles(int[][] m) {
        int c = m[0].length;
        int[] res = new int[c];
        res[0] = 1;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 1) {
                    res[j] = 0;
                } else if (j > 0) {
                    res[j] += res[j - 1];
                }
            }
        }
        return res[c - 1];
    }
    
    //easy to understand version, 
    // thinking process:
    //so from top left to right bottom, typical back tracking, 
    // so i j is controlled by i-1, j and i j -1 so we want to 
    // have dp formula to carry on
    public int uniquePathsWithObstacles2(int[][] m) {
        int r = m.length;
        int c = m[0].length;
        if (m[0][0] == 1 || m[r - 1][c - 1] == 1)
            return 0;
        int[][] dp = new int[r + 1][c + 1];
        dp[1][1] = 1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (m[i][j] == 1) {
                    dp[i + 1][j + 1] = 0;
                    continue;
                }
                // we need to skip dp[0][1], starting point is dp[1][1]
                if (!(i == 0 && j == 0)) {
                    dp[i + 1][j + 1] = dp[i][j + 1] + dp[i + 1][j];
                }
            }
        }
        return dp[r][c];
    }
    
    // just make sure DP need to start from initialized ones
    public int uniquePathsWithObstacles_2D(int[][] A) {
        if (A == null || A.length < 1 || A[0].length < 1) return 0;
        
        
        int r = A.length, c =A[0].length;
        if (A[0][0] == 1 || A[r-1][c-1] == 1) return 0;
        int[][] dp =new int[r][c];
        
        dp[0][0] = 1;
        for(int i = 0; i< r; i++) {
            for(int j = 0; j< c;j++) {
                if (A[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i > 0) dp[i][j] += dp[i-1][j];
                if (j > 0) dp[i][j] += dp[i][j-1];
            }
        }
        
        return dp[r-1][c-1];
    }
    
    //so this version is the heritance of V1, we continue to use 2D DP and 
    //continue to use the same DP foruma, this is easier to understand
    public int uniquePathsWithObstacles_Path1_HeritanceV(int[][] m) {
        if (m == null || m.length < 1 || m[0].length < 1)  return 0;
        int r = m.length, c = m[0].length;
        
        int[][] dp = new int[r][c];
        dp[0][0] = m[0][0] == 1 ? 0 : 1;
        for(int i = 1; i < r; i++) {
            if (m[i][0] == 1) dp[i][0] = 0;
            else dp[i][0] = dp[i-1][0];
        }
        for(int i = 1; i< c;i++) {
            if (m[0][i] == 1) dp[0][i] = 0;
            else dp[0][i] = dp[0][i-1];
        }
        
        for(int i = 1; i < r;i++) {
            for(int j = 1; j< c;j++) {
                if (m[i][j] == 1) dp[i][j] = 0;
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[r-1][c-1];
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
