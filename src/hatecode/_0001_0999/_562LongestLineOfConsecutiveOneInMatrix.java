package hatecode._0001_0999;
public class _562LongestLineOfConsecutiveOneInMatrix {
/*
 * tags: google
562. Longest Line of Consecutive One in Matrix
Given a 01 matrix M, find the longest line of consecutive one in the matrix. 
The line could be horizontal, vertical, diagonal or anti-diagonal.
Example:
Input:

[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3

*/ 
   //DP, O(mn)/O(1) 
    public int longestLine(int[][] A) {
        if (A.length == 0 || A[0].length == 0) return 0;
        int m = A.length, n = A[0].length;
        int max = 0, hori = 0, vert = 0, inc = 0, desc = 0;
        //horizonte scan 
        for (int i = 0; i < m; i++, hori = 0) {
            for (int j = 0; j < n; j++) {
                hori = A[i][j] > 0 ? hori + 1 : 0;
                max = Math.max(max, hori);
            }
        }
        //vertically scan 
        for (int j = 0; j < n; j++, vert = 0) {
            for (int i = 0; i < m; i++) {
                vert = A[i][j] > 0 ? vert + 1 : 0;
                max = Math.max(max, vert);
            }
        }
        for (int k = 0; k < m + n; k++, inc = 0, desc = 0) {
            // increasing start from left cells then bottom cells
            for (int i = Math.min(k, m - 1), j = Math.max(0, k - m); i >= 0 && j < n; i--, j++) {
                inc = A[i][j] > 0 ? inc + 1 : 0;
                max = Math.max(max, inc);
            }
            // decreasing start from left cells then top cells;
            for (int i = Math.max(m - 1 - k, 0), j = Math.max(0, k - m); i < m && j < n; i++, j++) {
                desc = A[i][j] > 0 ? desc + 1 : 0;
                max = Math.max(max, desc);
            }
        }
        return max;        
    }
    //O(mn)/O(mn), interview friendly
    public int longestLine3(int[][] M) {
        if (M == null || M.length < 1 || M[0].length < 1) return 0;
        int r = M.length, c = M[0].length, max = 0;
        //0 is horizonte, 1 is anti-dialog, 2 is vertical, 3 is dialog
        //dp[i][j][0], means (0,0)->(i,j) the longest horizonte line
        int[][][] dp = new int[r][c][4];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {
                if (M[i][j] == 0) continue;
                //initialize
                for (int k = 0; k < 4; k++) dp[i][j][k] = 1;
                // horizontal line
                if (j > 0) dp[i][j][0] += dp[i][j - 1][0]; 
                // vertical line
                if (i > 0) dp[i][j][2] += dp[i - 1][j][2]; 
                // anti-diagonal line
                if (j > 0 && i > 0) dp[i][j][1] += dp[i - 1][j - 1][1];
                // diagonal line
                if (j < c - 1 && i > 0) dp[i][j][3] += dp[i - 1][j + 1][3]; 
                max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
                max = Math.max(max, Math.max(dp[i][j][2], dp[i][j][3]));
            }
        return max;
    }
    
    public int longestLine4(int[][] M) {
        if (M.length == 0 || M[0].length == 0) {
            return 0;
        }
        int max = 0;
        int[] col = new int[M[0].length];
        int[] diag = new int[M.length + M[0].length];
        int[] antiD = new int[M.length + M[0].length];
        for (int i = 0; i < M.length; i++) {
            int row = 0;
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    row++;
                    col[j]++;
                    diag[j + i]++;
                    antiD[j - i + M.length]++;
                    max = Math.max(max, row);
                    max = Math.max(max, col[j]);
                    max = Math.max(max, diag[j + i]);
                    max = Math.max(max, antiD[j - i + M.length]);
                } else {
                    row = 0;
                    col[j] = 0;
                    diag[j + i] = 0;
                    antiD[j - i + M.length] = 0;
                }
            }
        }
    return max;
}


    
    
    
    public int longestLine2(int[][] M) {
        if (M == null || M.length < 1 || M[0].length < 1) return 0;
        int r = M.length, c = M[0].length;
        int res = 0;
        for(int i = 0; i< r;i++) {
            for(int j = 0; j< c;j++) {
                if (M[i][j] == 0) continue;
                res = Math.max(res, helper(M, i, j, r, c));
            }
        }
        return res;
    }
    
    private int helper(int[][] m, int i, int j, int r, int c) {
        int[][] dirs = {{1,0},{0,1}, {1,1}, {1,-1}};
        int res = 1;
        for(int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            int count = 1;
            while(validate(m,x,y,r,c)) {
                count +=1;
                x += dir[0];
                y += dir[1];
            }
            res = Math.max(res, count);
        }
        return res;
    }
    
    private boolean validate(int[][]m, int i, int j, int r, int c){
        if (i < 0  || j < 0 || i >= r || j>=c || m[i][j] == 0) {
            return false;
        } else return true;
    }
}