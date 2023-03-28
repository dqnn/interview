package hatecode._1000_1999;
public class _1155NumberOfDiceRollsWithTargetSum {
/*
1155. Number of Dice Rolls With Target Sum
You have d dice, and each die has f faces numbered 1, 2, ..., f.

Return the number of possible ways (out of f^d total ways) modulo 10^9 + 7 to 
roll the dice so the sum of the face up numbers equals target.

Example 1:

Input: d = 1, k = 6, target = 3
Output: 1
*/
  
    //thinking process: O(d* target)/O(target)
    
    //the problem is to say: given d dices, one which k faces, the value on each face is 
    //1...f, each dice you can play once, so return how many ways of values sequence sum = target, 
    //the result m % 10^9 + 7
    
    //we can use brute force to solve first, f(d, f, target) = f(d-1, f, target - i)+.....
    //so we can see we have 2 dimensions, one is d another is sum, so
    //we can memo the middle value, 
    
    //continue the solution, TODO: understand this solution
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
    
    //bottom up solution, DP with dfs, but not space optimization, be careful about the 
    //MOD
    /*
     * dp[i][j]: for i dices, sum of them are j, how many sequences are there?
     * 
     * dp[i][j] = dp[i-1][j-1] + d[i-1][j-2]....+dp[i-1][j-k]
     * 
     * thinking from sequence perspective, it would be crystal clear
     * 
     * how we initialize dp.
     * since sum of all sequences  equals to target, so it would be dp[0][0] means 
     * we used all dices and target reduced to 0.
     * 
     *      */
    public int numRollsToTarget_DP2(int d, int k, int target) {
        int MOD = (int)Math.pow(10, 9) + 7;
        long[][] dp = new long[d + 1][target + 1];
        
        //TODO: why only initialize here can get correct results;
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++) {
            for (int j = 1; j <= target; j++) {
                for (int p = 1; p <= k; p++) {
                    if (j >= p) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - p]) % MOD;
                    } else break;
                }
            }
        }
        return (int)dp[d][target];
    }
    
    //interview friendly firstly but not space optimized
    //we store res + 1 because most results are 0, we still want to remember that 
    //we already done the computation
    /*
     * f(d, k, target)--> how many sequsences(its len = d, its sum is target), 
     * for example 
     * f(1,6,3) will have (3), just one sequence
     * f(2,6,7) will have (1,6), (2,5), (3,4), (4,3), (5,2), (6,1)--> 6 sequences
     * 
     * so f(d, k, t)= f(d-1, k,t-1)+...+f(d-k,k, t-k)
     * which means sequence (1,...x), (2....y) ... (3....), each length = d and sum = t
     * 
     * 
     */
    int[][] dp = new int[31][1001];
    int M = 1000000007;
    //top-down
    int numRollsToTarget_DP(int d, int k, int target) {
        if (d == 0 || target <= 0) return d == target ? 1 : 0;
        //some dp[d][target] will have 0 results, but we need it to say we have computed dp[d][target] 
        //so we want it dp[d][target] > 0, then return 
        //but when we return the real result, we still return the real result
        if (dp[d][target] > 0) return dp[d][target] - 1;
        
        int res = 0;
        for (int i = 1; i <= k; ++i)
            res = (res + numRollsToTarget(d - 1, k, target - i)) % M;
        
        dp[d][target] = res + 1;
        return res;
    }
    
    
    public int numRollsToTarget_BF_Overflow(int d, int f, int target) {
        int res = 0;
        int M = 1000000007;
        for(int i = 1; i<=f; i++) {
            res = (res + numRollsToTarget(d-1, f, target - i)) % M;
        }
        
        return res;
    }
}