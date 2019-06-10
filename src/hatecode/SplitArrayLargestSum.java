package hatecode;

import java.util.*;
public class SplitArrayLargestSum {
/*
1014. Capacity To Ship Packages Within D Days
410. Split Array Largest Sum

tags: backtracking->memo->DP->binary search

Given the number of bags,
return the minimum capacity of each bag,
so that we can put items one by one into all bags.

Similar as
875. Koko Eating Bananas
774. Minimize Max Distance to Gas Station

Given an array which consists of non-negative integers and an integer m, you can 
split the array into m non-empty continuous subarrays. Write an algorithm to minimize 
the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18
*/  //O(len(nums) * m) = ( O(N * m))/O(nm)
    
    //thinking process: we have an array, need to split into m groups and contious numbers
    //so dfs with index and group number but there are 2 dimension recursive, so not easy. 
    
    //we use presum and visited to simplify the calculation, 
    //first we have cut them two parts, then left has result, then right into dfs again and we 
    //compare each until we get the result. 
    public int splitArray2(int[] nums, int m) {
        int n = nums.length;
        int[] presum = new int[n+1];
        presum[0] = 0;
        //prefix sum
        for (int i = 1; i <= n; i++) {
            presum[i] += nums[i-1] + presum[i-1];
        }
        //visited means [start][m] 
        int[][] visited = new int[n][m+1];
        //start from 0 and m groups
        return helper(0, m, nums, presum, visited);
    }
    //m means groups
    private int helper(int start, int m, int[] nums, int[] presum, int[][] visited) {
        //like edge case, only 1 group left,so we just calculate the result
        if (m == 1) {
            return presum[nums.length] - presum[start];
        }
        
        if (visited[start][m] != 0) {
            return visited[start][m];
        }
        
        int maxSum = Integer.MAX_VALUE;
        //this is templates for groups and nums to get max for subarray problems
        /*
        [7,2,5,10,8] m  =2, 
          /        \ 
      7 | 2 5 10 8   7 2|... and we have 4 child here 
       / \
   2|5 10 8 .... like this 
   
so we use visited[start][m] as mem to record which we have visited
*/      // i last position is nums.length - 2 since m > 1
        for (int i = start; i < nums.length-1; i++) {
            //left means first group sum
            int left = presum[i+1] - presum[start];
            //this will be recursive calc to each group
            int rightIntervalMax = helper(i+1, m-1, nums, presum, visited);
            //this would pick max sum for each group
            maxSum = Math.min(maxSum, Math.max(left, rightIntervalMax));
            
        }
        //record result
        visited[start][m] = maxSum;
        return maxSum;
    }
    
    //O(nlogsum) best solutions
    public int splitArray3(int[] nums, int m) {
        int max = 0; long sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (m == 1) return (int)sum;
        //binary search
        long l = max; long r = sum;
        while (l <= r) {
            long mid = l + (r - l) /2;
            if (valid(mid, nums, m)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        //so we return left as minimized sum
        return (int)l;
    }
    //all num are non-negative, so suppose all sum, and max number. and if we can divide 
    //m groups, each group avg(sum) compare to mid = (max + sum) / 2
    // if we found we have more groups if we compare each group to continous sum
    public boolean valid(long target, int[] nums, int m) {
        int curGroupCnt = 1;
        long total = 0;
        for(int num : nums) {
            total += num;
            if (total > target) {
                //we reset total from each group by a new number
                //but this could help to make sure previous group less than mid
                total = num;
                curGroupCnt++;
                //exceed total group number
                if (curGroupCnt > m) return false;
            }
        }
        return true;
    }

    //DP, O(n^2m)/O(n)
    /*
    dp[s,j] is the solution for splitting subarray n[j]...n[L-1] into s parts.
     dp[s+1,i] = min{ max(dp[s,j], n[i]+...+n[j-1]) }, i+1 <= j <= L-s
    */
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 0; i < n; i++)
            sum[i + 1] = sum[i] + nums[i];

        int[] dp = new int[n];
        for (int i = 0; i < n; i++)
            dp[i] = sum[n] - sum[i];

        for (int s = 1; s < m; s++) {
            for (int i = 0; i < n - s; i++) {
                dp[i] = Integer.MAX_VALUE;
                for (int j = i + 1; j <= n - s; j++) {
                    int t = Math.max(dp[j], sum[j] - sum[i]);
                    if (t <= dp[i]) dp[i] = t;
                    else break;
                }
            }
        }
        return dp[0];
    }
    //O(n^2m)/O(nm)
    //dp[i][j] to be the minimum largest subarray sum for splitting nums[0..i] into j parts
    //Consider the jth subarray. We can split the array from a smaller index k to i to form it. 
    //Thus f[i][j] can be derived from max(f[k][j - 1], nums[k + 1] + ... + nums[i])
    
    //one learn from this perspective is that we can work on A[0---i] into j parts which is really 
    //unique perspective
    public int splitArray_DP(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
        int[] pSum = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + nums[i];
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], pSum[i] - pSum[k]));
                }
            }
        }
        return dp[n][m];
    }
}