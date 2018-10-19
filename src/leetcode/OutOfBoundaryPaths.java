package leetcode;

import java.util.Arrays;

/*
 * author Denny Du 
 * Date 10/18/2018
576. Out of Boundary Paths
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, 
you can move the ball to adjacent cell or cross the grid boundary in four 
directions (up, down, left, right). However, you can at most move N times. 
Find out the number of paths to move the ball out of grid boundary. The answer may be very large, 
return it after mod 109 + 7.

 

Example 1:

Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:

Example 2:

Input: m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:

 

Note:

Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].
 */
public class OutOfBoundaryPaths {
    //DP[i][j][k] stands for how many possible ways to walk into cell j,k in step i, DP[i][j][k] 
    //only depends on DP[i - 1][j][k], so we can compress 3 dimensional dp array to 2 dimensional.
    public int findPaths(int m, int n, int N, int i, int j) {
        if (N <= 0) return 0;
        
        final int MOD = 1000000007;
        int[][] count = new int[m][n];
        // i and j is the ball coordination
        count[i][j] = 1;
        int result = 0;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //we turn steps into a for loop
        for (int step = 0; step < N; step++) {
            int[][] temp = new int[m][n];
            // the two for are visit each cell in the matrix
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    // 4 directions, r c is original
                    for (int[] d : dirs) {
                        int nr = r + d[0];
                        int nc = c + d[1];
                        //outside
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                            // 
                            result = (result + count[r][c]) % MOD;
                        } else {//inside the matrix
                            temp[nr][nc] = (temp[nr][nc] + count[r][c]) % MOD;
                        }
                    }
                }
            }
            //we change the DP 2D array to the temp one
            count = temp;
        }
        return result;
    }
    
    
    //memo[i][j][k] is used to store the number of possible moves leading to a path 
    //out of the boundary if the current position is given by the indices (i, j)(i,j) 
    //and number of moves left is kk.
    //Thus, now if a function call with some parameters is repeated, the memomemo array 
    //will already contain valid values corresponding to that function call resulting 
    //in pruning of the search space.

    int M = 1000000007;

    public int findPaths3(int m, int n, int N, int i, int j) {
        int[][][] memo = new int[m][n][N + 1];
        for (int[][] l : memo)
            for (int[] sl : l)
                Arrays.fill(sl, -1);
        return findPaths(m, n, N, i, j, memo);
    }

    public int findPaths(int m, int n, int N, int i, int j, int[][][] memo) {
        if (i == m || j == n || i < 0 || j < 0)
            return 1;
        if (N == 0)
            return 0;
        if (memo[i][j][N] >= 0)
            return memo[i][j][N];
        memo[i][j][N] = (( findPaths(m, n, N - 1, i - 1, j, memo) 
                         + findPaths(m, n, N - 1, i + 1, j, memo)) % M
                         + (findPaths(m, n, N - 1, i, j - 1, memo) 
                         + findPaths(m, n, N - 1, i, j + 1, memo)) % M) % M;
        return memo[i][j][N];
    }
    
    //this is TLE, the problem for the case:
    // if N > 0 while we return to the cell we visited still this can be correct, as example 2. 
    
    int count = 0;
    public int findPaths2(int m, int n, int N, int i, int j) {
        int[][] visited = new int[m][n];
        dfs(m, n, N, i, j, visited);
        return count;
    }
    
    private void dfs(int m, int n, int N, int i, int j, int[][] visited) {
        if (i < 0 || j < 0 || i >= m || j >= n) {
            if (N >= 0) {
                count += 1;
            }
            return;
        }
        //so if all steps are done, then leave
        if (N < 0) {
            return;
        }
        if (visited[i][j] == 1) {
            return;
        }
        
        //visited[i][j] = 1;
        
        dfs(m, n, N - 1, i+1, j, visited);
        dfs(m, n, N - 1, i-1, j, visited);
        dfs(m, n, N - 1, i, j + 1, visited);
        dfs(m, n, N - 1, i, j - 1, visited);
        //visited[i][j] = 0;
    }
}