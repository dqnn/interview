package hatecode._0001_0999;
public class _877StoneGame {
/*
877. Stone Game
Input: [5,3,4,5]
Output: true
Explanation: 
Alex starts first, and can only take the first 5 or the last 5.
Say he takes the first 5, so that the row becomes [3, 4, 5].
*/
    
    //same as 486. Predict the Winner
    public boolean stoneGame(int[] piles) {
        int N = piles.length;
        int[][] memo = new int[N][N];
        return helper(piles, 0, N - 1, memo) > 0;
    }
    
    public int helper(int[] nums, int s, int e, int[][] memo) {
        if (s == e) return nums[s];
        
        if (memo[s][e] != 0) return memo[s][e];
        
        int a = nums[s] - helper(nums, s+1, e, memo);
        int b = nums[e] - helper(nums, s, e-1, memo);
        memo[s][e] = Math.max(a, b);
        return memo[s][e];
    }
}