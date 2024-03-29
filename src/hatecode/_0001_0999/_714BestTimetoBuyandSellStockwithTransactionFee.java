package hatecode._0001_0999;
public class _714BestTimetoBuyandSellStockwithTransactionFee {
    /*
     * 

714. Best Time to Buy and Sell Stock with Transaction Fee
     * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

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
	
    
    /*
    prices = [1, 3, 2, 8, 4, 9], fee = 2
     noStock={0,   0,  0,  5,  5,  8}
   withStock={-1, -1, -1, -1,  1,  1}
    */
    public int maxProfit(int[] nums, int fee) {
        //edge case
        if (nums == null || nums.length < 1) {
            return 0;
        }
        
        int len = nums.length;
        //withStock means by the end of day i, if i have stock, the max profit i have.
        //for first day, the profit is - nums[0], 
        //noStock[i] means by end of  i-day, we have no stock in hand, so first 
        //nostock[0] = 0
        int[] noStock = new int[len], withStock = new int[len];
        noStock[0] = 0;
        withStock[0] = - nums[0];
        for(int i = 1; i <= nums.length - 1; i++) {
            //nostock we have 2 options, 1 is to buy, 2nd to hold, but we want to get max profit
            //so we max of two of them
            noStock[i] = Math.max(noStock[i - 1], withStock[i - 1] + nums[i] - fee);
            //withStock means we have 2 options, 1 is to sell, another is to hold
            withStock[i] = Math.max(withStock[i - 1], noStock[i - 1] - nums[i]);
        }
        //finally we have no stock in hand, so we want to know the max profit
        return noStock[len - 1];
    }
}