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
 *       50
 *   1-------3
 *           10
 *      2---------4
 *               40
 *           3---------5
 *                    70
 *           3-----------------6
 * 
 * we use dp[i] means for for ith task, the max profit, 
 * then from dp[i] to dp[i+1]
 * 
 * option 1: = dp[i], we drop job[i]
 * option 2: we do job[i] but we need to find how can we connect previous job, the critirial is to look for the end time <= current start time (binary search)
 * we want to find from dp[1],dp[2],dp[3]...dp[i]
 * which job end time <= job[i][0] start time, then it will be dp[j] + job[i][2]
 * 
 * one key is in binary search: we want to find the most close to job[i][0], which means the last element in array, so we would like to move left when equals to target
 * ,target is job[i][0], think about find the last element in array, 
 * 
 * another key is we still have l and r two elements, first check whether r is more fit because we want to check the last element, if not check the l, 
 * but l also not qualified.
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
        
        int[] dp = new int[n];
        
        dp[0] = jobs[0][2];
        
        for(int i = 1; i<n; i++) {
            dp[i] = dp[i-1];
            
            int l = 0, r = i;
            
            while(l + 1 < r) {
                int m = l + (r-l)/2;
                // we want to find last one which qualified,so l = m, dp[i] will non -decreasing
                if(jobs[i][0] >= jobs[m][1]) {
                   l = m;
                } else r = m;
            }
            
            if(jobs[r][1] <= jobs[i][0]) {
                dp[i] = Math.max(dp[r] + jobs[i][2], dp[i]);
            } else if(jobs[l][1] <= jobs[i][0]) {
                dp[i] = Math.max(dp[l] + jobs[i][2], dp[i]);
            } else {
                dp[i] = Math.max(jobs[i][2], dp[i]);
            }
            
        }
        
        
        return dp[n-1];
    }
}