package hatecode._0001_0999;

import java.util.*;

public class _983MinimumCostForTickets {
/*
983. Minimum Cost For Tickets

You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.

Train tickets are sold in three different ways:

a 1-day pass is sold for costs[0] dollars,
a 7-day pass is sold for costs[1] dollars, and
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.

For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given list of days.

 

Example 1:

Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
*/

/*
 * thinking process: O(max(days))/O(max(days))
 * 
 * the problem is to say: days[i] means the days[i] will start on vacation, say days=[1,2, 5] means you will be on vacation on 1th, 2th, 5th day of the year,
 * then cost=[2,3,5] means you can buy pass for 1d, 7d 30d cost,,
 * 
 * return the minimal cost for all days
 * 
 * dp[i] means for day i, the minimal cost,
 * 
 * dp[i] = min(dp[i-1] + cost[0], dp[i-2]+cost[1], dp[i-7]+cost[2])
 * 
 * one thing need to note if days between two travelling dates, we need to maintain the cost, that's why 
 * dp[i] = d[i-1], 
 * 
 * for example 
 * dp[days[0]] = cost[0] 
 * 
 * dp[days[1]] = if two  days span within one week, , then min(2*cost[0], cost[1], cost) or 
 * 
 * 
 */
    public static int mincostTickets(int[] days, int[] costs) {
        if(days == null || days.length < 1) return 0;
        
        int n = 30 + 1 + Arrays.stream(days).max().getAsInt();
        boolean[] isTravelDay = new boolean[n];
        for (int day : days) {
            isTravelDay[day] = true;
        }
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (!isTravelDay[i]) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = Integer.MAX_VALUE;
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 1)] + costs[0]);
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 7)] + costs[1]);
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 30)] + costs[2]);
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        System.out.println(mincostTickets(new int[]{1,4,6,7,8,20}, new int[]{2,7,15}));
    }
}