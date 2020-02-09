package hatecode._0001_0999;
import java.util.*;
public class SplitArrayWithSameAverage {
/*
805. Split Array With Same Average
In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)

Return true if and only if after such a move, it is possible that the average value of B is equal to the average value of C, and B and C are both non-empty.

Example :
Input: 
[1,2,3,4,5,6,7,8]
Output: true
*/
    //Brute force  by combination SUM, TLE, just to understand the problem
    public boolean splitArraySameAverage_BackTracking(int[] A) {
        if (A == null || A.length < 1) return false;
        
        int n = A.length, m = n / 2; 
        int sum = Arrays.stream(A).sum();
        //Arrays.sort(A);
        
        for(int i = 1; i <=m; i++) {
            if (sum * i % n == 0 && combinationSum(A, 0, i, sum * i / n)) return true;
        }
        return false;
    }
    
    private boolean combinationSum(int[] A, int pos, int k, int tar) {
        //System.out.println(tar + "--" + k);
        if ( k <0 || tar < 0) return false;
        
        if (k == 0) return tar == 0;
        for(int i = pos; i <= A.length - k; i++) {
            if (A[i] <= tar && combinationSum(A, i+1, k-1, tar - A[i])) return true;
        }
        
        return false;
        
    }
/*
 Runtime analysis:
All numbers in the array are in range [0, 10000]. Let M = 10000.
So the size of kth combination sum hashset, i.e. sums[...][k], is <= k * M;
For each number in the array, the code need loop through all combination sum hashsets, so
the total runtime is n * (1 * M + 2 * M + ... + (n/2) * M) = O(n^3 * M)

 */
    // best solution, O(n^2* M), M = max(A)
    public boolean splitArraySameAverage(int[] A) {
        if (A == null || A.length < 1) return false;
        
        int n = A.length, m = n / 2; 
        int sum = Arrays.stream(A).sum();
        
        boolean[][] dp = new boolean[sum + 1][m + 1];
        dp[0][0] = true;
        //TODO: this is knapsack, should visit this again
        for(int num : A) {
            for(int i = sum; i>=num; i--) {
                for(int j = 1;j <=m; j++) {
                    dp[i][j] = dp[i][j] || dp[i-num][j-1];
                }
            }
        }
        
        for(int i= 1; i <= m; i++) {
            if (sum * i % n == 0 && dp[sum * i / n][i]) return true;
        }
        
        return false;
    
    }
    
}