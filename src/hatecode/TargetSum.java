package hatecode;
import java.util.*;
public class TargetSum {
/*
 tag: DP
494. Target Sum
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
*/
     //DP solution, this is knapsack problems
    public int findTargetSumWays2(int[] nums, int S) {
        int totalSum = Arrays.stream(nums).sum();
        //this can be removed
        for (int num : nums) {  //calculate the totalSum keeping all the elements in the array positive
            totalSum += num;  
        }
        if (totalSum < S || -totalSum > S) { //If the target sum S is not reachable by the range
            return 0;
        }
        //2* sum + 1 means any one is possible
        int[] dp = new int[2 * totalSum + 1];
         //dp[i] -> the number of ways to have sum = i - totalSum
        dp[totalSum] = 1; 
        //We start from no elements in the array, so there is one way to have 
        //sum = 0 that there is no element
        for (int i = 0; i < nums.length; i++) { //Start from deciding to add the first element as positive or negative
            int[] next = new int[2 * totalSum + 1];
            for (int j = 0; j < 2 * totalSum + 1; j++) {
                if (dp[j] != 0) {  //if current sum j - totalSum is already reached by the previous searched numbers
                    next[j + nums[i]] += dp[j]; //plus the num of ways to have sum = j - totalSum to the num of ways to have sum = j + nums[i] - totalSum 
                    next[j - nums[i]] += dp[j];
                }//The previous reached range is  -totalSum < [-currSum, currSum ] < totalSum.
                //Since currSum + nums[i] no larger than totalSum, -currSum - nums[i] no smaller than -totalSum, so it won't exceed the boundary
            }
            dp = next;
        }
        return dp[S + totalSum]; //return the num of ways to have sum = S
    }
    

    //setup model in the position of operators, and using backtracking to solve it
    int count = 0;
    //O(ln)/O(n), l is range of sum, n is nums length
    public int findTargetSumWays(int[] nums, int target) {
        //memo[i][j] means for nums[0->i], sum = j combinations
        //sum <=1000, the real sum must be [-sum, sum], so we would have max =2000, so we use 2001
        int[][] memo = new int[nums.length][2001];
        for (int[] row: memo) Arrays.fill(row, Integer.MIN_VALUE);
        
        return calculate(nums, 0, 0, target, memo);
    }

    public int calculate(int[] nums, int i, int curSum, int target, int[][] memo) {
        if (i == nums.length) {
            return curSum == target ? 1 : 0;
        }
        if (memo[i][curSum + 1000] != Integer.MIN_VALUE) {
            return memo[i][curSum + 1000];
        }
        int add = calculate(nums, i + 1, curSum + nums[i], target, memo);
        int subtract = calculate(nums, i + 1, curSum - nums[i], target, memo);
        memo[i][curSum + 1000] = add + subtract;
        return memo[i][curSum + 1000];
    }
}