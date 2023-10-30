package hatecode._2000_2999;

import java.util.*;

public class _2742PaintingTheWalls {
    /*
2742. Painting the Walls

    You are given two 0-indexed integer arrays, cost and time, of size n representing the costs and the time taken to paint n different walls respectively. There are two painters available:

A paid painter that paints the ith wall in time[i] units of time and takes cost[i] units of money.
A free painter that paints any wall in 1 unit of time at a cost of 0. But the free painter can only be used if the paid painter is already occupied.
Return the minimum amount of money required to paint the n walls.

 

Example 1:

Input: cost = [1,2,3,2], time = [1,2,3,2]
Output: 3
Explanation: The walls at index 0 and 1 will be painted by the paid painter, and it will take 3 units of time; meanwhile, the free painter will paint the walls at index 2 and 3, free of cost in 2 units of time. Thus, the total cost is 1 + 2 = 3.
    

    paid worker finished walls + free paited walls = n

    paid work work time  + paid worker finished walls >=  n 


    (paid worker work time + 1) >= n,

    how to choose time, so sum(time[i] +1) >=n, the lowest cost

    */

    /*
     * thinking process: O(n^2)/O(n)
     * 
     * the problem is to say: given two integer array cost and time,  time[i] means time of painting i-th wall, cost[i]
     * means the cost of paiting i-th wall, they are for paid worker, 
     * free painter can paint wall in 1 unit time and 0 cost, but free paint worker can only work when paid worker is working 
     * 
     * cost = [1,2,3,2], time = [1,2,3,2]
     * 
     * if paid work is working on 0 and 1 walls, then free work can work for 1+2 =3 units of time 
     * 
     * 
     * 
     */
    public int paintWalls(int[] cost, int[] time) {
        int n = time.length;

        int[] dp = new int[n+1];
        Arrays.fill(dp, (int)1e9);
        dp[0] = 0;

        for(int i = 0; i<n; i++) {
            for(int j = n; j>0; j--) {
                dp[j] = Math.min(dp[j], dp[Math.max(j - time[i] -1, 0)] + cost[i]);
            }
        }

        return dp[n];
    }
}