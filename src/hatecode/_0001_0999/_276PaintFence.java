package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PaintFence
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 276. Paint Fence
 */
public class _276PaintFence {
    /**
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Example:

Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

            post1  post2  post3      
 -----      -----  -----  -----       
   1         c1     c1     c2 
   2         c1     c2     c1 
   3         c1     c2     c2 
   4         c2     c1     c1  
   5         c2     c1     c2
   6         c2     c2     c1

     1 2 3

     不同: (k - 1) * total
     相同: 2 3 相同，= 2和1 不同的种类

     time : O(n)
     space : O(1)

     * @param n
     * @param k
     * @return
     */
    
    //interview friendly, thinking process:
    //so given n posts and k color, we required no same color on adjacent posts, how many ways,
    // so total = dp[i] = last_two_same * (k-1) + last_two_diff * k-1; 
    //last_two_diff * k-1;  this is easy to understand,
    //for last_two_same * (k-1), we just need i-2 is different than i-1 and i, so we have (k-1) choices
    public int numWays(int n, int k) {
        if (n < 1 || k < 1) return  0;
        if (n == 1) return k;
        if (n == 2) return k * k;
        //dp[i] means the fence i with k colors, how many ways of painting
        //1. if i-1 and i are the same, 
        //2  if i-1 and i are not the same, we need to 
        //num_ways(i) = num_ways_diff(i) + num_ways_same(i)
        //num_ways_diff[i] = (k-1) * num_ways_diff[i-1]
        //num_ways_same[i] = num_ways(i-2) * (k-1)， so we just need to make sure last 2 posts have different color compares to 
        //nums[i-2], so we have k - 1 choices
        //dp[i] has 2 case: 
        
        //dp[i] = (k-1) * dp[i-1]
        
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = k;
        dp[2] = k * k;
        for(int i = 3; i <= n; i++) {
            dp[i] = (dp[i-1]+dp[i-2]) * (k-1);
        }
        return dp[n];
        
        
    }
    
    
    public int numWays_reference(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        int same = 0, diff = k, total = k;
        for (int i = 2; i <= n; i++) {
            same = diff;
            diff = (k - 1) * total;
            total = same + diff;
        }
        return total;
    }
}
