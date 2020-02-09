package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : HouseRobber
 * Creator : duqiang
 * Date : July, 2018
 * Description : 198. House Robber
 */
public class HouseRobber {
    /**
     * You are a professional robber planning to rob houses along a street. Each
     * house has a certain amount of money stashed, the only constraint stopping you
     * from robbing each of them is that adjacent houses have security system
     * connected and it will automatically contact the police if two adjacent houses
     * were broken into on the same night.
     * 
     * Given a list of non-negative integers representing the amount of money of
     * each house, determine the maximum amount of money you can rob tonight without
     * alerting the police.
     * 
     * Example 1:
     * 
     * Input: [1,2,3,1] Output: 4 Explanation: Rob house 1 (money = 1) and then rob
     * house 3 (money = 3). Total amount you can rob = 1 + 3 = 4. Example 2:
     * 
     * Input: [2,7,9,3,1] Output: 12 Explanation: Rob house 1 (money = 2), rob house
     * 3 (money = 9) and rob house 5 (money = 1). Total amount you can rob = 2 + 9 +
     * 1 = 12.
     * 
     * time : O(n) space : O(1)
     * 
     * @param nums
     * @return
     */
    //two states change to each other
    //means for current house i, rob or not rob. 
    public int rob(int[] nums) {
        int prevNo = 0;
        int prevYes = 0;
        for (int num : nums) {
            int temp = prevNo;
            // we choose preYes sum compare to previous 
            prevNo = Math.max(prevNo, prevYes);
            prevYes = num + temp;
        }
        return Math.max(prevNo, prevYes);
    }
    
    //1-dimension DP,
    //typical DP problem
    public int rob_DP(int[] A) {
        if (A == null ||A.length < 1) return 0;
        int n = A.length;
        int[] dp = new int[n+1];
        dp[0] = 0; dp[1] = A[0];
        for(int i = 2; i<=n; i++) dp[i] = Math.max(dp[i-2] + A[i-1], dp[i-1]);

        return dp[n];
    }
    
    
    public int rob2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        } 
        int len = nums.length;
         // dp[][] 2 means rob or not rob
        int[][] dp = new int[len + 1][2];
         for (int i = 1; i <= len; i++) {
             // we decide not to rob at i
             dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
             // we decide not rob at i
             dp[i][1] = nums[i - 1] + dp[i - 1][0];
         }
         
         return Math.max(dp[len][0], dp[len][1]);
        
     }
}
