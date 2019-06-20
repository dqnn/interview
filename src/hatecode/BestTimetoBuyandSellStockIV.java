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
    //interview friendly and thinking process: 
    //so we have 5 questions about how to buy stock, need more time to reflect on this point
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        //if k more than half of the price array, then we have to buy or sell each day because 
        //each price will be same, so we have to try to make positive profits
        // so each day price is the same, so each we either buy or sell, 
        //so if k is more than half of the length, we can greedy to buy or sell each day
        // if we are allowed to do len/2 or more transactions, we can convert to
        //problem of Stock II by using greedy algorithm
        if (k >= len / 2) {
            int result = 0;
            for (int i = 1; i < len; i++) {
                if (prices[i] > prices[ i - 1]) {
                    result += prices[i] - prices[i - 1];
                }
            }
            return result;
        }
        /** sub-problem:
         * dp[i][j] represents max profit of first j + 1 prices by making i transactions， 
         * j starts from 0 so it is j + 1 transactions
         *
         * base case:
         * dp[0][j] = 0 for 0 < j < prices.length, since 0 transaction will have no profit
         * dp[i][0] = 0 for 0 < i <= k, since there does not have any available prices
         */
        int[][] dp = new int[k + 1][len];
        //so we have two loops K and length of prices, we want to know 
        //if k = 1 which means only 1 transaction, what kind of profit we can have, 
        //so dp[i][j] is trying to detect if we only make one transaction, what's the profit. 
        for (int i = 1; i <= k; i++) {
         // assume we buy stock at the first price, this is like withStock[0] = - prices[0]，
            //is the max Money Before Sell.
            int prevMax = -prices[0];
            for (int j = 1; j < len; j++) {
                /* dp[i][j] deciding the selling point
                 *  Similar to 0-1 knapsack problem, we have two candidates at here (use or not use):
                 *  1. if we do not use current new available price:
                 *     keep previous max profit dp[i][j-1] at current new available price without doing any new transaction
                 *  2. if we use current new available price:
                 *     throw previous max, update new max profit by doing one more transaction at current new price (bought new stock before, and sell it on current transaction)
                 *  By comparing these two max profit, we will keep the one with max value, and assign to dp[i][j]
                 * */
                //we keep i transactions and j prices, we want to compare if we hold or buying 
                //stock at new price prices[j], 
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + prevMax);
                /* preMax deciding the buying point
                 *  in order to prepare previous price state for dp[i][j] in next iteration, we need to calculate
                 *  whether we want to use current price as buying price :
                 *  1. if we do not use one more transaction chance to buy new stock, and keep original buying price:
                 *     keep previous max profit, as preMax
                 *  2. if we use one more transaction chance to buy new stock:
                 *     use previous max profit with i - 1 transaction dp[i-1][j] minus new buying price, price[j],
                 *     then we will decide new max profit in next iteration
                 * */
                prevMax = Math.max(prevMax, dp[i - 1][j - 1] - prices[j]);
            }
        }
/*
Input: k= 2  prices = [3,2,6,5,0,3]
DP 
[0, 0, 0, 0, 0, 0]
[0, 0, 4, 4, 4, 4]
[0, 0, 4, 4, 4, 7]
 */
        return dp[k][len - 1];
    }
    //another solution. k >= len/2 means you can sell/buy every day since "at most", this 
    // become the infinite use case
    public int maxProfit_SimpleDP(int k, int[] p) {
        if (p == null) return 0;
        int n = p.length;
        int res = 0;
        if (k >= n/2){
            for(int i=1; i< n;i++){
                if (p[i] > p[i-1]) res += p[i] -p[i-1];
            }
            return res;
        }
        //this is the key here:
        //1. length is k + 1
        //2. k transaction is inner loop for each price
        //3. transaction j > 0,no =, because we have to use j-1 in as index
        //4. the formula, noStock->ownStock[j], we use same index j, not j-1, but for 
        //ownstock, the formula is noStock->ownStock[j-1]
        //because when state from ownStock to noStock, because we sell stocks at current price
        //but when we buy the stock, our moneday did not change from last time, so we use 
        //noStock[j-1] - price
        int[] ownStock = new int[k+1];
        int[] noStock = new int[k+1]; //profit at k transactions
        //Arrays.fill(ownStock, -p[0]); also works
        Arrays.fill(ownStock, Integer.MIN_VALUE);
        for(int price:p) {
            for(int j = 1; j <=k;j++) {
                //so if we did not buy, we cannot sell, so noStock
                noStock[j]= Math.max(noStock[j], ownStock[j] + price);
                ownStock[j]= Math.max(ownStock[j], noStock[j-1] - price);
            }
        }
        return noStock[k];
    }
}
