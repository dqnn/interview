    package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ClimbingStairs
 * Date : Nov, 2017
 * Description : 70. Climbing Stairs
 */
public class _070ClimbingStairs {
    /**
     * You are climbing a stair case. It takes n steps to reach to the top.

     Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

     Note: Given n will be a positive integer.


     Example 1:

     Input: 2
     Output:  2
     Explanation:  There are two ways to climb to the top.

     1. 1 step + 1 step
     2. 2 steps

     time : O(n)
     space : O(n)/O(1)

     * @param n
     * @return
     */

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        } else {
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }

    // similiar to DP
    public int climbStairs2(int n) {
        if (n <= 1) return 1;
        int oneStep = 1, twoStep = 1, res = 0;
        for (int i = 2; i <= n; i++) {
            res = oneStep + twoStep;
            twoStep = oneStep;
            oneStep = res;
        }
        return res;
    }

    // DP, which means recursive maybe transformed to DP
    public int climbStairs_DP(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for(int i =3; i<=n; i++) dp[i] = dp[i-1] + dp[i-2];
        
        return dp[n];
    }
}
