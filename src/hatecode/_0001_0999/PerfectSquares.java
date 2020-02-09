package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PerfectSquares
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 279. Perfect Squares
 */
public class PerfectSquares {

    /**
     * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...)
     * which sum to n.

     For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

     time : O(n * sqrt(n))
     space: O(n)

     * @param n
     * @return
     */

    /*
dp[0] = 0 
dp[1] = dp[0]+1 = 1
dp[2] = dp[1]+1 = 2
dp[3] = dp[2]+1 = 3
dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 } 
      = Min{ dp[3]+1, dp[0]+1 } 
      = 1               
dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 } 
      = Min{ dp[4]+1, dp[1]+1 } 
      = 2

dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 } 
       = Min{ dp[12]+1, dp[9]+1, dp[4]+1 } 
       = 2

dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
     */
    
    //back pack extension, size is n, we have stones like 1, 2^2, 3^3, to find the fewest 
    //stones to fill it
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
             // we use j * j to re-calc the res array every time, so we have the opportunity to 
                // find the least number to get it
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
    //same solution, but we transformed the problem to a standard knap sack questions
    public int numSquares2(int n) {
        if (n <= 0) return 0;
        
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int m = (int)Math.sqrt(n) + 1;
        dp[0] = 0;
        for(int j = 1; j < m; j++) {
        for(int i =1; i <= n;i++) {
              if(i >= j * j)        
                dp[i] = Math.min(dp[i], dp[i- j* j] + 1);
            }
        }
        return dp[n];
    }
}
