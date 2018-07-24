package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : GuessNumberHigherorLowerII
 * Creator : duqiang
 * Date : Jan, 2018
 * Description : 375. Guess Number Higher or Lower II
 */
public class GuessNumberHigherorLowerII {
    /**
     * We are playing the Guess Game. The game is as follows:

     I pick a number from 1 to n. You have to guess which number I picked.

     Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

     However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

     Example:

     n = 10, I pick 8.

     First round:  You guess 5, I tell you that it's higher. You pay $5.
     Second round: You guess 7, I tell you that it's higher. You pay $7.
     Third round:  You guess 9, I tell you that it's lower. You pay $9.

     Game over. 8 is the number I picked.

     You end up paying $5 + $7 + $9 = $21.
     Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

     1 2 3 4 5 6 7 (8) 9 10

     dp[i][j] : i - j 肯定赢所需多少钱

     最小的最大值问题：
     i - j 中，任意猜一个数x，获胜所花的钱为 x + max(helper(i ,x-1), helper(x+1 ,j))

     5 : 10 11

     有一种理解方式就是猜一个数字 两个数字 以及N个数字 需要花的最少的钱
     1,for
     2,dfs + memo

     time : O(n^3)
     space : O(n^2)


     * @param n
     * @return
     */

    int[][] dp;

    public int getMoneyAmount(int n) {
        dp = new int[n + 1][n + 1];
        return helper(1, n);
    }
/*
 * dp[i][j] is the minimal cost to guess from range(i...j).
When you choose an x where i <= x <= j, you may find the target number from left i...x-1, 
or you may find the target number from the x+1...j, because you don't know which way should go, 
so to guarantee you have enough money to find the target, you need to prepare the more, which is
 max(dp[i][x-1], dp[x+1][j]).
 */
    private int helper(int i, int j) {
        if (i > j) return 0;
        if (dp[i][j] != 0) return dp[i][j];
        int res = Integer.MAX_VALUE;
        for (int x = i; x <= j; x++) {
            res = Math.min(res, Math.max(helper(i, x - 1), helper(x + 1, j)) + x);
        }
        dp[i][j] = res;
        return res;
    }

    /**                    x
     * 1 2 3 4 5 6 7 (8) 9 10
     *             i        j
     * @param n
     * @return
     */

    public int getMoneyAmount2(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i > 0; i--) {
            for (int j = i + 1; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int x = i; x < j; x++) {
                 // the max means whenever you choose a number, the feedback is always 
                    //bad and therefore leads you to a worse branch.
                   // this min makes sure that you are minimizing your cost.
                    // k+DP( start, k-1 ) + DP(k+1, end ),
                    dp[i][j] = Math.min(dp[i][j], x + Math.max(dp[i][x - 1], dp[x + 1][j]));
                }
            }
        }
        return dp[1][n];
    }
}
