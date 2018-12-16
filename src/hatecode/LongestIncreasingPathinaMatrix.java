package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestIncreasingPathinaMatrix
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 329. Longest Increasing Path in a Matrix
 */
public class LongestIncreasingPathinaMatrix {
    /**
     * Given an integer matrix, find the length of the longest increasing path.

     From each cell, you can either move to four directions: left, right, up or down.
     You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

     Example 1:

     nums = [
     [9,9,4],
     [6,6,8],
     [2,1,1]
     ]
     Return 4
     The longest increasing path is [1, 2, 6, 9].

     Example 2:

     nums = [
     [3,4,5],
     [3,2,6],
     [2,2,1]
     ]
     Return 4
     The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

     time : O(m * n)
     space : O(m * n)

     */
    // typical backtracing and dfs
    public int longestIncreasingPath(int[][] m) {
        if (m == null || m.length < 1) {
            return 0;
        }
        
        int r = m.length, c = m[0].length, max = 0;
        int[][] visited = new int[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                max = Math.max(max, dfs(m, i, j, visited, Integer.MIN_VALUE));
            }
        }
        
        return max;
    }
    
    public int dfs(int[][] m, int i, int j, int[][] visited, int min) {
        if (i < 0 || j < 0 || i >= m.length || j >= m[0].length || m[i][j] <= min) {
            return 0;
        }
        
        if (visited[i][j] != 0) {
            return visited[i][j];
        }
        // we replace min because it is increasing path
        min = m[i][j];
        int up = dfs(m, i - 1, j, visited, min) + 1;
        int down = dfs(m, i + 1, j, visited, min) + 1;
        int left = dfs(m, i, j - 1, visited, min) + 1;
        int right = dfs(m, i, j + 1, visited, min) + 1;
        visited[i][j] = Math.max(up, Math.max(down, Math.max(left, right)));
        
        return visited[i][j];
    }
}
