package hatecode._0001_0999;

/**
 * Description : 329. Longest Increasing Path in a Matrix
 */
public class _329LongestIncreasingPathinaMatrix {
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
    // interview friendly, O(mn)/O(mn) each cell will be visiting once and only once
    
    //thinking process: 
    //Given a 2D matrix, we want to find the longest increasing path, return how many numbers in this path.
    //so we need to go through each element in matrix, we used a cache 2D array to cache each element max path
    //if start from this element, the max path.
    
    //so each element will just once even we have to try every element, which means helper() will be finish in 
    //constant time mn + 1....+ 1(mn) = 2 mn
    public int longestIncreasingPath(int[][] m) {
        if(m == null || m.length < 1 || m[0].length < 1) return 0;
        
        int r = m.length, c=m[0].length;
        int res = Integer.MIN_VALUE;
        int[][] memo = new int[r][c];
        for(int i = 0; i< r; i++) {
            for(int j = 0; j< c; j++) {
                res = Math.max(res, helper(m, i, j, memo));
            }
        }
        return res;
    }
    
    private int helper(int[][] m, int i, int j, int[][] memo) {
        if(memo[i][j] > 0) return memo[i][j];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int r = m.length, c = m[0].length;
        //each path at least 1 element
        int max = 1;
        for(int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x < 0 || x >= r || y < 0 ||  y  >= c  ||  m[x][y] <= m[i][j]) continue;

            // int dis = helper(m, x, y, memo) + 1;
            //System.out.println(i + "," + j + "->" + x + "," + y + "=" + dis);
            max = Math.max(max, 1 + helper(A, x, y, memo));
        }
        // here is important, because evey [i, j] will be assigned one value
        memo[i][j] = max;
        return memo[i][j];
    }
}
