package hatecode._1000_1999;
public class _1035UncrossedLines {
/*
1035. Uncrossed Lines
We write the integers of A and B (in the order they are given) on two separate horizontal lines.

Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:

A[i] == B[j];
The line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.

Return the maximum number of connecting lines we can draw in this way.
*/
    //thinking process:O(rc)/O(rc)
    
    //the problems is to say: given two integer array A and B,
    //we draw a line if A[i]=B[j], but the line cannot intersect, so return max 
    //lines we can draw.
    
    //LCS problem ,the longest common subsequence
    public int maxUncrossedLines(int[] A, int[] B) {
        if(A == null || A.length < 1 || B == null || B.length < 1) return 0;
        
        int r = A.length, c = B.length;
        int[][] dp = new int[r+1][c+1];
        for(int i = 1; i<= r; i++) {
            for(int j =1; j<=c; j++) {
                if(A[i-1] == B[j-1]) dp[i][j] = 1+dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        return dp[r][c];
    }
}