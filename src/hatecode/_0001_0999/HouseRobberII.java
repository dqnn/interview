package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : HouseRobberII
 * Creator : duqiang
 * Date : July, 2018
 * Description : 213. House Robber II
 */

/*
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobberII {
    
    
    /**
     * time : O(n)
     * space : O(1)
     * @param nums
     * @return
     */
    
    /*
     * However, since we already have a nice solution to the simpler problem. We do not want to throw it 
     * away. Then, it becomes how can we reduce this problem to the simpler one. Actually, 
     * extending from the logic that if house i is not robbed, then you are free to choose 
     * whether to rob house i + 1, you can break the circle by assuming a house is not robbed.

For example, 1 -> 2 -> 3 -> 1 becomes 2 -> 3 if 1 is not robbed.

Since every house is either robbed or not robbed and at least half of the houses are not robbed, 
the solution is simply the larger of two cases with consecutive houses, i.e. house i not robbed, 
break the circle, solve it, or house i + 1 not robbed. Hence, the following solution. 
I chose i = n and i + 1 = 0 for simpler coding. But, you can choose whichever two consecutive ones.
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) return nums[0];
        // we try 0 to len - 2 and 1 to len - 1, 
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    public int rob(int[] nums, int lo, int hi) {
        int prevNo = 0;
        int prevYes = 0;
        for (int i = lo; i <= hi; i++) {
            int temp = prevNo;
            prevNo = Math.max(prevNo, prevYes);
            prevYes = nums[i] + temp;
        }
        return Math.max(prevNo, prevYes);
    }
    
    //this is my own solutions
    public int rob_DP(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        int n = A.length;
        if (n == 1) return A[0];
        if (n == 2) return Math.max(A[0], A[1]);
        
        return Math.max(Rob(A, 0, n-2), Rob(A, 1, n-1));
    }
    
    private int Rob(int[] A, int start, int end) {
        //this is the same to have result, but return fast
        if (start == end - 1) return Math.max(A[start], A[end]);
        int n = end - start + 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = A[start];
        
        for(int i = 2; i <= n; i++){
            dp[i] = Math.max(dp[i-2] + A[i-1 + start], dp[i-1]);
        }
        return dp[n];
    }
}
