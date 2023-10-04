package hatecode._1000_1999;

import java.util.*;

public class _1235MaximumProfitInJobScheduling {
/*
1235. Maximum Profit in Job Scheduling

We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

 

Example 1:



Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
*/


/*
 * thinking process: O(nlgn)/O(n)
 * 
 * the problem is to say, given 3 arrays, s, e and p, s[i], e[i] means one job start and end time, p[i] is the profit, 
 * you need return the max profit but make sure there is no overlap between jobs
 *    s   [1, 2,  3, 3]
 *    e   [3, 4,  5, 6]
 *    p   [50,10,40,70]
 * 
 * we sort the jobs array by end time, so they align on right side, then 
 * 
 */
    public int jobScheduling(int[] s, int[] e, int[] p) {
        if(s == null || s.length < 1) return 0;
        
        int n = s.length;
        int[][] jobs = new int[n][3];
        for(int i = 0; i< n; i++) {
            jobs[i] = new int[]{s[i], e[i], p[i]};
        }
        
        Arrays.sort(jobs, (a, b)->Integer.compare(a[1], b[1]));
        
        int[] dp = new int[n+1];
        
        for(int i = 0; i<n; i++) {
            dp[i+1] = Math.max(dp[i+1], dp[i]);
            
            int l = 0, r = i;
            
            while(l + 1 < r) {
                int m = l + (r-l)/2;
                if(jobs[i][0] >= jobs[m][1]) {
                    l = m;
                } else r = m;
            }
            
            if(jobs[r][1] <= jobs[i][0]) {
                dp[i+1] = Math.max(dp[r+1] + jobs[i][2], dp[i+1]);
            } else if(jobs[l][1] <= jobs[i][0]) {
                dp[i+1] = Math.max(dp[l+1] + jobs[i][2], dp[i+1]);
            } else {
                dp[i+1] = Math.max(jobs[i][2], dp[i+1]);
            }
            
        }
        
        
        return dp[n];
    }
}