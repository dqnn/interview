package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _518CoinChangeII {
/*
518.
Coin Change 2
You are given coins of different denominations and a total 
amount of money. Write a function to compute the number of 
combinations that make up that amount. You may assume that 
you have infinite number of each kind of coin.

Note: You can assume that

0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
 

Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
 

Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
 

Example 3:

Input: amount = 10, coins = [10] 
Output: 1
 */
/*

dp[i][j] : the number of combinations to make up amount j by using the first i types of coins
State transition:

not using the ith coin, only using the first i-1 coins to make up amount j, then we have dp[i-1][j] ways.
using the ith coin, since we can use unlimited same coin, we need to know how many ways to make up 
amount j - coins[i-1] by using first i coins(including ith), which is dp[i][j-coins[i-1]]
Initialization: dp[i][0] = 1

Once you figure out all these, it's easy to write out the code:
 */
    //thinking process:
    
    //the problem is to say coins = [1,2,5] and amount =12
    //how many combinations we have assume coins are unlimited
    
    //dp[i][j], i means i types of coins, j means amount, whole means how many
    //combinations if we use i types of coin to form j
    /*a= 5, coins=[1,2,5]
    0  1  2  3  4  5
 0  1  0  0  0  0  0
 1  1  1  1  1  1  1 
 2  1  1  2  2  3  3
 5  1  1  2  2  2  4

 */
    public int change3(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        dp[0][0] = 1;
        
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }
    //thinking about this way:
    
    //dp[i] += dp[i -coin], 
    public static int change2(int a, int[] c) {
        if (a <= 0) return 1;
        if (c == null || c.length < 1) return 0;
        int[] dp = new int[a + 1];
        dp[0] = 1;
        for (int coin : c) {
            //starts from coin, we do not need to i >= coin
           // for (int i = coin; i <= a; i++) {
             //   dp[i] = dp[i] + dp[i - coin];
            //}
            for (int i = a; i >=coin; i--) {
                dp[i] = dp[i] + dp[i - coin];
            }
        }
        return dp[a];
    }


    public static void main(String[] args) {
        System.out.println(change2(5, new int[]{1,2,5}));
    }
    
    public int change(int a, int[] c) {
        //this is just for compatible with result in lc
        if (a <=0) return 1;
        if (c == null || c.length < 1) return 0;
        Arrays.sort(c);
        Set<List<Integer>> res = new HashSet<>();
        helper(res, new ArrayList<>(), a, c, 0);
        return res.size();
    }
    
    public void helper(Set<List<Integer>> res, List<Integer> list, int a, int[] c, int start) {
        if (a < 0) return;
        if (a == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
       
        for(int i = start; i < c.length; i++) {
            if (a - c[i] < 0) continue;
            list.add(c[i]);
            helper(res, list, a-c[i], c, i);
            list.remove(list.size() - 1);
        }
    }
}