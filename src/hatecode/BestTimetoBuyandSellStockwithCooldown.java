package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BestTimetoBuyandSellStockwithCooldown
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 309. Best Time to Buy and Sell Stock with Cooldown
 */
public class BestTimetoBuyandSellStockwithCooldown {
    /**
     * buy[i]表示在第i天之前最后一个操作是买，此时的最大收益。

     sell[i]表示在第i天之前最后一个操作是卖，此时的最大收益。

     rest[i]表示在第i天之前最后一个操作是冷冻期，此时的最大收益。

     我们写出递推式为：

     buy[i]  = max(rest[i-1] - price, buy[i-1])
     sell[i] = max(buy[i-1] + price, sell[i-1])
     rest[i] = max(sell[i-1], buy[i-1], rest[i-1])

     上述递推式很好的表示了在买之前有冷冻期，买之前要卖掉之前的股票。一个小技巧是如何保证[buy, rest, buy]的情况不会出现，
     这是由于buy[i] <= rest[i]， 即rest[i] = max(sell[i-1], rest[i-1])，这保证了[buy, rest, buy]不会出现。

     另外，由于冷冻期的存在，我们可以得出rest[i] = sell[i-1]，这样，我们可以将上面三个递推式精简到两个：

     buy[i]  = max(sell[i-2] - price, buy[i-1])
     sell[i] = max(buy[i-1] + price, sell[i-1])

     time : O(n)
     space : O(1)

     * @param prices
     * @return
     */
    //interview friendly
    //thinking process:  we thought there are 3 states during whole operation time, 
    //S0 represents 
/*
           buy         reset
         S1<-----S0 ()<----S2
         S1--->S2
            buy
 each of these states can reset return to same
  s0[i] = max(s0[i-1], s2[i-1])
  s1[i] = max(s1[i-1], s0[i-1] - prices[i-1])
  s2[i] = s1[i-1] + prices[i-1)  only one way
 
 explanations for the states 
 s0:
[Consequence] State not immediate after selling; Doesn't have any stock;
[Action can take] Buy a new stock / Continue to take a rest

s1:
[Consequence] State with stock in hand;
[Action can take] Sell a stock / Continue to take a rest

s2:
[Consequence] State immediate after selling; Doesn't have any stock (since just 
sell one to enter this state)
[Action can take] Enters next state by taking a rest (since s2 is only for state 
immediate after selling, we cannot stay here.)
 */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 1) {
            return 0;
        }
       
        int[] s0 = new int[len]; //start state (or state after rest)
        int[] s1 = new int[len]; //state after buying
        int[] s2 = new int[len]; //state after selling

        s0[0] = 0;
        s1[0] = -prices[0];
        s2[0] = Integer.MIN_VALUE;

        for (int i = 1; i < len; i++) {
            /* for current state of s0, we may stay on s0 state to rest many days, or transfer from s2 state to rest one day */
            s0[i] = Math.max(s0[i - 1], s2[i - 1]);

            /* for current state of s1, we may stay on s1 state to rest many days (wait better opportunity to buy), or transfer from s0 state to buy at current price */
            s1[i] = Math.max(s1[i - 1], s0[i - 1] - prices[i]);

            /* for current state of s2, we may stay on s2 state to rest many days (wait better opportunity to sell), or transfer from s1 state to sell (at current price) on the second day after buying */
            s2[i] = Math.max(s2[i - 1], s1[i - 1] + prices[i]);
        }
        return Math.max(s0[len - 1], s2[len - 1]);
    }
    
    public int maxProfit_reference(int[] prices) {
        int sell = 0, prevSell = 0;
        int buy = Integer.MIN_VALUE, prevBuy = 0;
        for (int price : prices) {
            prevBuy = buy;
            buy = Math.max(prevSell - price, prevBuy);
            prevSell = sell;
            sell = Math.max(prevBuy + price, prevSell);
        }
        return sell;
    }
}
