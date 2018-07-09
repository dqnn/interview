package leetcode;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CoinChange
 * Creator : duqiang
 * Date : Jan, 2018
 * Description : 322. Coin Change
 */
public class CoinChange {
    /**
     * ou are given coins of different denominations and a total amount of money
     * amount. Write a function to compute the fewest number of coins that you need
     * to make up that amount. If that amount of money cannot be made up by any
     * combination of the coins, return -1.
     * 
     * Example 1: coins = [1, 2, 5], amount = 11 return 3 (11 = 5 + 5 + 1)
     * 
     * Example 2: coins = [2], amount = 3 return -1.
     * 
     * dp[] amount 需要多少coins 这里其实是 15块的问题转换成10块的问题 + 1个硬币 或则14块的问题加一个硬币 这个很巧妙 min =
     * Math.min(min, dp[i - coins[j]] + 1);
     * 
     * time : O(n*amount) space : O(amount)
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins == null || coins.length == 0) return -1;

        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != -1) {
                    min = Math.min(min, dp[i - coins[j]] + 1);
                }
            }
            // here means we cannot find any coins for this amount, so we have to
            // use -1 here
            dp[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return dp[amount];
    }

    // dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1); 其实和上面的一样 Bottom Up
    public int coinChange2(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max); // means max value is amount + 1, all 1
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
