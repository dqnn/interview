package hatecode._0001_0999;
public class _813LargestSumOfAverages {
/*
813. Largest Sum of Averages
We partition a row of numbers A into at most K adjacent (non-empty) groups, 
then our score is the sum of the average of each group. What is the largest score we can achieve?

Note that our partition must use every number in A, and that scores are not necessarily integers.

Example:
Input: 
A = [9,1,2,3,9]
K = 3
Output: 20 = (9)/1 + (1+2+3)/3 + (9)/1
*/  
    //typical backtracking solution
    //thinking process:O(kn^2)
    //given an array, and K as max partition number, return the max avg for the array
    
    //K should be 1 and A.length, it is like a cut, we throw 1 ~ K-1 cuts in the array. and get is avg and compare 
    //each time, so this is a backtracking problem. 
    //dp[i][k] means for 0~i and k groups so far, the max sum of avg 
    //k was computed by backtracking method and i was computed by from n-1 to 1
    public double largestSumOfAverages(int[] A, int K) {
        if (A == null ||A.length < K) return 0.0;
        int n = A.length;
        //dp[n][k] means for 0--n array with k partition, the max avg
        double[][] dp = new double[n+1][n+1];
        
        double cur = 0.0;
        //first we get only 1 partition, the avg
        for(int i = 0; i<n; i++) {
            cur += A[i];
            dp[i+1][1] = cur/(i+1);
        }
        
        return helper(n, K, A, dp);
    }
    
    private double  helper(int n, int k, int[] A, double[][] dp) {
        if (dp[n][k] > 0) return dp[n][k];
        
        if (n < k) return 0;
        
        double cur = 0.0;
        //we scan from right to left, each time we reduce the partition, and compare to get the best one
        for(int i= n-1; i>0;i--) {
            cur += A[i];
            dp[n][k] = Math.max(dp[n][k], helper(i, k -1, A, dp) + cur/(n - i));
        }
        
        return dp[n][k];
    }
}