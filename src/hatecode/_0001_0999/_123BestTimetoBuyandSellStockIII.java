package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BestTimetoBuyandSellStockIII
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 123. Best Time to Buy and Sell Stock III
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

You may complete as up to 2 transactions as you like,
Return the maximum profit you can make.

Example 1:
Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Note:

0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.
 */
public class _123BestTimetoBuyandSellStockIII {

    /**
     * time : O(n)
     * space : O(1)
     * @param prices
     * @return
     */
/*
 * T[i][k][0]: 最多K次交易后当天的手里有0股股票的最大利润
 * T[i][k][1]: 最多K次交易后当天手里有1股股票的最大利润
 * 所以每一天只能有个状态，一个是手里股票，一个事手里没股票，前一个分两种，一个是买 ，另一个hold， 后一个也有两个
 * 一个是卖，二是hold
 */
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        for (int price : prices) {
            sell2 = Math.max(sell2, buy2 + price);
            buy2 = Math.max(buy2, sell1 - price); // buy second time
            sell1 = Math.max(sell1, buy1 + price);
            buy1 = Math.max(buy1, -price);
        }
        return sell2;
    }
}
