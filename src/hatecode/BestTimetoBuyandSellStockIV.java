package hatecode;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BestTimetoBuyandSellStockIV
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 188. Best Time to Buy and Sell Stock IV
 */
public class BestTimetoBuyandSellStockIV {
    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.

     Design an algorithm to find the maximum profit. You may complete at most k transactions.


     dp[i, j] 当前到达第j天可以最多进行i次交易，最大的利润是多少
     tmpMax means the maximum profit of just doing at most i-1 transactions, using at most first j-1 prices,
     and buying the stock at price[j] - this is used for the next loop.


     time : O(k * n)
     space : O(k * n)

     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return helper(prices);
        //dp[i, j] 当前到达第j天可以最多进行i次交易，最大的利润是多少
        int[][] dp = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmpMax = -prices[0];
            for (int j = 1; j < len; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + tmpMax);
                tmpMax = Math.max(tmpMax, dp[i - 1][j - 1] - prices[j]);
            }
        }
        return dp[k][len - 1];
    }

    public int helper(int[] prices) {
        int len = prices.length;
        int res = 0;
        for (int i = 1; i < len; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
    
    //another solution. k >= len/2 means you can sell/buy every day since "at most", this 
    // become the infinite use case
    public int maxProfit2(int k, int[] prices) {
        if (k >= prices.length >>> 1) {
            int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
        
            for (int price : prices) {
                int T_ik0_old = T_ik0;
                T_ik0 = Math.max(T_ik0, T_ik1 + price);
                T_ik1 = Math.max(T_ik1, T_ik0_old - price);
            }
            
            return T_ik0;
        }
            
        int[] T_ik0 = new int[k + 1];
        int[] T_ik1 = new int[k + 1];
        Arrays.fill(T_ik1, Integer.MIN_VALUE);
            
        for (int price : prices) {
            for (int j = k; j > 0; j--) {
                T_ik0[j] = Math.max(T_ik0[j], T_ik1[j] + price);
                T_ik1[j] = Math.max(T_ik1[j], T_ik0[j - 1] - price);
            }
        }
            
        return T_ik0[k];
    }
}
