package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BurstBalloons
 * Creator : duqiang
 * Date : Jan, 2018
 * Description : 312. Burst Balloons
 */
public class BurstBalloons {
    /**
     * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on
     * it represented by array nums. You are asked to burst all the balloons.
     * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
     * Here left and right are adjacent indices of i. After the burst,
     * the left and right then becomes adjacent.

     Find the maximum coins you can collect by bursting the balloons wisely.

     Note:
     (1) You may imagine nums[-1] =å nums[n] = 1. They are not real therefore you can not burst them.
     (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

     Example:

     Given [3, 1, 5, 8]

     Return 167

     nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
       i     j
     1 3 1 5 8 1
       3 1   8
     1     5   1

     dp[i][j]为打破的气球为i~j之间
     dp[i][j] = max(dp[i][j], dp[i][x – 1] + nums[i – 1] * nums[x] * nums[j + 1] + dp[x + 1][j]);

     1 for
     2 dfs + memo

     time : O(n^3)
     space : O(n^2)

     [3, 1, 5, 8]
     1 3 1 5 8

     * @param nums
     * @return
     */

    public int maxCoins(int[] nums) {
        int n = nums.length;
        //copy original data into new ones and add 1 in start and end
        int[] arr = new int[n + 2];
        for (int i = 0; i < n; i++) arr[i + 1] = nums[i];
        arr[0] = arr[n + 1] = 1;
        
        int[][] dp = new int[n + 2][n + 2];
        return helper(1, n, arr, dp);
    }
    //helper is aim to calc (i,j) the max ballon burst sum, 
    //(i, x-1), x, (x+1, j)
    private int helper(int i, int j, int[] nums, int[][] dp) {
        if (i > j) return 0;
        if (dp[i][j] > 0) return dp[i][j];
        for (int x = i; x <= j; x++) {
            // for writing recursive, we need to understand how many data you need to touch
            // especially when we divide
            dp[i][j] = Math.max(dp[i][j], helper(i, x - 1, nums, dp)
                                + nums[i - 1] * nums[x] * nums[j + 1]
                                + helper(x + 1, j, nums, dp));
        }
        return dp[i][j];
    }

    /*
     * Be Naive First
     * 
     * When I first get this problem, it is far from dynamic programming to me. I
     * started with the most naive idea the backtracking.
     * 
     * We have n balloons to burst, which mean we have n steps in the game. In the i
     * th step we have n-i balloons to burst, i = 0~n-1. Therefore we are looking at
     * an algorithm of O(n!). Well, it is slow, probably works for n < 12 only.
     * 
     * Of course this is not the point to implement it. We need to identify the
     * redundant works we did in it and try to optimize.
     * 
     * Well, we can find that for any balloons left the maxCoins does not depends on
     * the balloons already bursted. This indicate that we can use memorization (top
     * down) or dynamic programming (bottom up) for all the cases from small numbers
     * of balloon until n balloons. How many cases are there? For k balloons there
     * are C(n, k) cases and for each case it need to scan the k balloons to
     * compare. The sum is quite big still. It is better than O(n!) but worse than
     * O(2^n).
     * 
     * Better idea
     * 
     * We then think can we apply the divide and conquer technique? After all there
     * seems to be many self similar sub problems from the previous analysis.
     * 
     * Well, the nature way to divide the problem is burst one balloon and separate
     * the balloons into 2 sub sections one on the left and one one the right.
     * However, in this problem the left and right become adjacent and have effects
     * on the maxCoins in the future.
     * 
     * Then another interesting idea come up. Which is quite often seen in dp
     * problem analysis. That is reverse thinking. Like I said the coins you get for
     * a balloon does not depend on the balloons already burst. Therefore instead of
     * divide the problem by the first balloon to burst, we divide the problem by
     * the last balloon to burst.
     * 
     * Why is that? Because only the first and last balloons we are sure of their
     * adjacent balloons before hand!
     * 
     * For the first we have nums[i-1]*nums[i]*nums[i+1] for the last we have
     * nums[-1]*nums[i]*nums[n].
     * 
     * OK. Think about n balloons if i is the last one to burst, what now?
     * 
     * We can see that the balloons is again separated into 2 sections. But this
     * time since the balloon i is the last balloon of all to burst, the left and
     * right section now has well defined boundary and do not affect each other!
     * Therefore we can do either recursive method with memoization or dp.
     * 
     * Final
     * 
     * Here comes the final solutions. Note that we put 2 balloons with 1 as
     * boundaries and also burst all the zero balloons in the first round since they
     * won't give any coins. The algorithm runs in O(n^3) which can be easily seen
     * from the 3 loops in dp solution.
     */
    //interview friendly O(n ^ 3)
    public int maxCoins2(int[] iNums) {
        int len = iNums.length;
        int n = len + 2;
       int[] nums = new int[n];
       for (int i = 0; i< len;i++)
            //if (iNums[i] > 0)//we can remove this
                nums[i+1] = iNums[i];
        nums[0] = nums[len+1] = 1;
        System.out.println(n);
        //dp[1][len] will be the result
        int[][] dp = new int[n][n];
        
        //dp[i][j] means new array nums, from i to j, max coins
        //it has 3 parts, suppose k is last to burst, so we walk 1 balloon, 2 balloons, etc 
         for(int l=1;l<=len;++l)
            //i and j is the sub array, l is the length of the sub array
            for(int i=1;i<=len-l+1;++i) {
                int j=i+l-1;
                //k is the index between i and j
                for(int k=i;k<=j;++k) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k-1] + nums[i-1] * nums[k] * nums[j+1] + dp[k+1][j]);
                }
            }
        
        return dp[1][len];
        
    }

}
