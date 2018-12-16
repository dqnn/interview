package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UniquePaths
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 62. Unique Paths
 */
public class UniquePaths {
/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the 
diagram below).

The robot can only move either down or right at any point in time. The robot is 
trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram 
below).

How many possible unique paths are there?
 */
    // time : O(n * m) space : (n * m)
    
    //thinking process:
    
    //the problem is to say, a robot in top-left, he can move down or right, so how many unique paths
    // from too-left to right bottom
    
    //first row and first column are marked 1. 
    // then visit each in res, res[i][j] = res[i-1][j] + res[i][j-1]
    //last we return res[m-1][n-1]
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            res[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            res[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }
        return res[m - 1][n - 1];
    }

    // time : O(n * m) space : O(n)
    // this is something more on the res array duplicate computations
    public int uniquePaths2(int m, int n) {
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[j] = res[j] + res[j - 1];
            }
        }
        return res[n - 1];
    }

    /**
     * Combination(Count, k) = count! / (k!*(count - k)!)
     * C = (count - k + 1) * (count - k + 2) .../k!
     * time : O(m)
     * space : (1)
     * @param m
     * @param n
     * @return
     */

    public int uniquePaths3(int m, int n) {
        int count = m + n - 2;
        int k = m - 1;
        double res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (count - k + 1) / i;
        }
        return (int)res;
    }
}
