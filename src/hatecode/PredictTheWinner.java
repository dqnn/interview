package hatecode;
public class PredictTheWinner {
/*
486. Predict the Winner
Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

Example 1:
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.
*/
    //thinking process: two players A and B and one given array, each time A or B can get first or last from array, at least
    //whose sum is bigger who will win, so return whether A will win or not
    
    //so suppose array is 1,5,4,8, so A have n/2 times to get one number, so total states will be O(2^n)/O(n), 
    //we use diff = first- sec >= 0, no tie, first would only have 2 choices, 1 or 8, so we use a and b to stands for.
/*
       1, 5,8,4
       /    \
     5, 8,4    1,5,8
     / \       / \
  8,4  5,8    5,8  1,5
  / \     /\    /\    /\
 4  8    8  5  8  5   5  1
 /   \   /\ /\ 
 
 first will occupy odd level while second will occupy even level so 
       
*/
    //O(2^n)/O(m)
    public boolean PredictTheWinner(int[] nums) {
        return dfs(nums, 0, nums.length - 1, 1) >= 0;
    }
    public int dfs(int[] nums, int s, int e, int turn) {
        if (s == e)
            return turn * nums[s];
        int a = turn * nums[s] + dfs(nums, s + 1, e, -turn);
        int b = turn * nums[e] + dfs(nums, s, e - 1, -turn);
        return turn * Math.max(turn * a, turn * b);
    }
    
    //memorize version 
    
    //memo is to remember given nums, [i, j] subarray, what's the score diff between player 1 and play2
    //top down way
    //O(n^2)/O(n^2), memo as 2D array, it can only be filled once
    public boolean PredictTheWinner2(int[] nums) {
        Integer[][] memo = new Integer[nums.length][nums.length];
        return helper(nums, 0, nums.length - 1, memo) >= 0;
    }
    //helper means one play can get the max score from the sub array, from s to e
    public int helper(int[] nums, int s, int e, Integer[][] memo) {
        if (s == e) return nums[s];
        if (memo[s][e] != null) return memo[s][e];
        
        int a = nums[s] - helper(nums, s + 1, e, memo);
        int b = nums[e] - helper(nums, s, e - 1, memo);
        memo[s][e] = Math.max(a, b);
        return memo[s][e];
    }
    
    //DP version  //O(n^2)/O(n^2)
    //dp[i][j] means for sub array(i,j) in nums, diff bewteen player 1 and conterparts best scoore he can get
    
    //so dp[i][j] = max(nums[i] - dp[i+1][j], nums[i] - dp[i][j-1]), 
    //boundary: so i dependson i+1, so start needs to back to front, and j depends j -1, so j can start from front to back
    //so two for loops, and row should have nums.length + 1 because we want nums.length should be in the dp 2D matrix
    public boolean PredictTheWinner3(int[] nums) {
        int[][] dp = new int[nums.length + 1][nums.length];
        for (int s = nums.length; s >= 0; s--) {
            for (int e = s + 1; e < nums.length; e++) {
                int a = nums[s] - dp[s + 1][e];
                int b = nums[e] - dp[s][e - 1];
                dp[s][e] = Math.max(a, b);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }
    //O(n^2)/O(n)
    //as above code, we can see only two rows value are needed, so we can overwrite the value in previous row, because old 
    //value does not need any more， here we overwrite the dp[e] value every time
    //compare to backsacks series problem, this is the same space optimization
    public boolean PredictTheWinner4(int[] nums) {
        int[] dp = new int[nums.length];
        for (int s = nums.length; s >= 0; s--) {
            for (int e = s + 1; e < nums.length; e++) {
                int a = nums[s] - dp[e];
                int b = nums[e] - dp[e - 1];
                dp[e] = Math.max(a, b);
            }
        }
        return dp[nums.length - 1] >= 0;
    }
    
}