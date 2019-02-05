package hatecode;
public class MinimumFallingPathSum {
/*
931. Minimum Falling Path Sum
Given a square array of integers A, we want the minimum sum of a falling path through A.

A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.

 

Example 1:

Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: 12
Explanation: 
The possible falling paths are:
*/
    public int minFallingPathSum(int[][] A) {
        if (A == null || A.length < 1 || A[0].length < 1) return 0;
        
        int N = A.length;
        for(int r = N - 2; r >=0 ;r--) {
            for(int c = 0; c < N; c++) {
                int best = A[r + 1][c];
                if (c > 0) best = Math.min(best, A[r+1][c-1]);
                if (c + 1 < N) best = Math.min(best, A[r+1][c+1]);
                A[r][c] += best;
            }
        }
        
        //return Arrays.stream(A[0]).min().getAsInt();
        int res = Integer.MAX_VALUE;
        for(int x : A[0]) {
            res = Math.min(res, x);
        }
        return res;
        
    }
}