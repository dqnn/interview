package hatecode._1000_1999;

import java.util.Arrays;

public class _1262GreatestSumDivisibleByThree {
    /*
    1262. Greatest Sum Divisible by Three

    Given an integer array nums, return the maximum possible sum of elements of the array such that it is divisible by three.

 

Example 1:

Input: nums = [3,6,5,1,8]
Output: 18
Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
Example 2:

Input: nums = [4]
Output: 0
Explanation: Since 4 is not divisible by 3, do not pick any number.

*/
    /*
    
    thinking process: O(n)/O(1)

    the problem is to say: given a integer array, you need to return max sum of elements in array, which the sum is divisble by 3; 

   for example, [3,6,5,1,8] --> 3,6, 1, 8= 18 is the correct answer

    
   so first understand the DP solution, then it will easier to understand the optimized solution 

   we use dp[i][3] means 

    [3,6,5,1,8]
    
    [3,0,0]
    [9,0,0]
    [9,0,14]
    [15,10,14]
    [18,22,23]
    
    */
    public int maxSumDivThree(int[] A) {
        if(A == null || A.length < 1) return 0;
        
        int[] res = new int[3];
        
        for(int num : A) {
            int a = res[0] + num;
            int b = res[1] + num;
            int c = res[2] + num;
            
            res[a%3] = Math.max(res[a%3], a);
            res[b%3] = Math.max(res[b%3], b);
            res[c%3] = Math.max(res[c%3], c);
        }
        
        return res[0];
    }
    
    /*
       [3,6,5,1,8]
     * dp :
     * [
     * 3:  [3,-2147483648,-2147483648],
     * 6:  [9,-2147483642,-2147483642],
     * 5:  [9,-2147483637,14],
     * 1:  [15,10,14],
     * 8:  [18,22,23]]
     * 
     * if we all initialize dp[0] as 0, then it will be:
     * dp: 
     * [ 
     * 3  [3,0,0],
     * 6  [9,6,6],
     * 5  [11,11,14],
     * 1  [15,12,14],
     * 8  [20,22,23]]
     * 
     * we can see dp[1][1] will become 6, 
     * 
     */
    public int maxSumDivThree_DP(int[] A) {
        if(A == null || A.length < 1) return 0;
        
        int n = A.length;
        int[][] dp = new int[n][3];
        for(int i = 0; i<n; i++) Arrays.fill(dp[i], Integer.MIN_VALUE);
        if(A[0] % 3 == 0) {
            dp[0][0] = A[0];
        } else {
            dp[0][A[0]%3] = A[0];
            dp[0][0] = 0;
        }
        
        for(int i = 1; i< n; i++) {
            if(A[i] % 3 == 0) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][0] + A[i]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][1] + A[i]);
                dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + A[i]);

            } else if(A[i]%3 ==1) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] + A[i]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + A[i]);
                dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + A[i]);

            } else {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + A[i]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][2] + A[i]);
                dp[i][2] = Math.max(dp[i-1][2], dp[i-1][0] + A[i]);
            }
            
        }
        
        return dp[n-1][0];
    }
}
