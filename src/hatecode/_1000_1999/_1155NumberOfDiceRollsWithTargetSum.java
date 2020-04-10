package hatecode._1000_1999;
public class _1155NumberOfDiceRollsWithTargetSum {
/*
1155. Number of Dice Rolls With Target Sum
You have d dice, and each die has f faces numbered 1, 2, ..., f.

Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.

 

Example 1:

Input: d = 1, f = 6, target = 3
Output: 1
*/
  
    //optimized DP
    public int numRollsToTarget(int d, int f, int target) {
        int mod = 1000000007;
        int[] dp1 = new int[target + 1];
        int[] dp2 = new int[target + 1];
        dp1[0] = 1;
        for(int i = 1; i <= d; i++) {
            int prev= dp1[0];
            for(int j = 1; j <= target; j++) {
                dp2[j] = prev;
                prev= (prev+ dp1[j]) % mod;
                if(j >= f) prev= (prev- dp1[j - f] + mod) % mod;
            }
            int[] temp = dp1;
            dp1 = dp2;
            dp2 = temp;
            dp2[0] = 0;
        }
        return dp1[target];
    }
    
    
    int[][] dp = new int[31][1001];
    long M = 10^9 + 7;
    //top-down
    int numRollsToTarget_DP(int d, int f, int target) {
        if (d == 0 || target <= 0) return d == target ? 1 : 0;
        if (dp[d][target] > 0) return dp[d][target] - 1;
        int res = 0;
        for (int i = 1; i <= f; ++i)
        res = (res + numRollsToTarget(d - 1, f, target - i)) % 1000000007;
        dp[d][target] = res + 1;
        return res;
    }
    
    
    public int numRollsToTarget_BF_Overflow(int d, int f, int target) {
        int res = 0;
        int M = 10^9 + 7;
        for(int i = 1; i<=f; i++) {
            res = (res + numRollsToTarget(d-1, f, target - i)) % M;
        }
        
        return res;
    }
}