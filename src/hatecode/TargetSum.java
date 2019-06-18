package hatecode;
import java.util.*;
public class TargetSum {
/*
 tag: DP, operators
494. Target Sum
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as 
its new symbol.

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
    
    //we map the dp to 2* sum + , [-sum, sum] elements, 
    //
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
    //O(ln)/O(n), l is range of sum, n is nums length
    int sum = 0;
    public int findTargetSumWays(int[] nums, int target) {
        //memo[i][j] means for nums[0->i], sum = j combinations
        //sum <=1000, the real sum must be [-sum, sum], so we would have max =2000, so we use 2001
        sum = Arrays.stream(nums).sum();
        int[][] memo = new int[nums.length][2*sum + 1];
        for (int[] row: memo) Arrays.fill(row, Integer.MIN_VALUE);
        
        return calculate(nums, 0, 0, target, memo);
    }
    //top-down solution, 
    //so when we 
    public int calculate(int[] nums, int i, int curSum, int target, int[][] memo) {
        if (i == nums.length) return curSum == target ? 1 : 0;

        if (memo[i][curSum + sum] != Integer.MIN_VALUE) {
            return memo[i][curSum + sum];
        }
        int add = calculate(nums, i + 1, curSum + nums[i], target, memo);
        int subtract = calculate(nums, i + 1, curSum - nums[i], target, memo);
        memo[i][curSum + sum] = add + subtract;
        return memo[i][curSum + sum];
    }
    
    /** how many ways we pick these elements which their sum = S
     * solution 2: DP (0/1 knapsack) - Time: O(n^2), Space: O(n^2) */
    /**
     * sub-problem: dp[i][j] represents number of possible ways to reach sum j by using first ith items
     * base case: dp[0][sum], position sum represents sum 0
     * recurrence relation:
     * dp[i][j] += dp[i - 1][j + nums[i - 1]] if j + nums[i - 1] <= sum * 2
     * dp[i][j] += dp[i - 1][j - nums[i - 1]] if j - nums[i - 1] >= 0
     * 
     * explanation: if j + nums[i - 1] or j - nums[i - 1] is in correct range, we can use the number nums[i - 1]
     * to generate next state of dp array
     * */
    public int findTargetSumWays_2D(int[] A, int target) {
        if (A.length == 0 || A.length < 1) return 0;

        int sum = Arrays.stream(A).sum();

        // corner case: when S is out of range [-sum, sum]
        if (target < -sum || target > sum) return 0;
        int n = A.length;
        int[][] dp = new int[n + 1][sum * 2 + 1];
        dp[0][sum] = 1;
        int leftBound = 0;
        int rightBound = sum * 2;
        for (int i = 1; i <= n; i++) {
            for (int j = leftBound; j < rightBound + 1; j++) {
                // try all possible sum of (previous sum j + current number nums[i - 1]) and all possible difference of
                // (previous sum j - current number nums[i - 1])
                if (j + A[i - 1] <= rightBound) {
                    dp[i][j] += dp[i - 1][j + A[i - 1]];
                }
                if (j - A[i - 1] >= leftBound) {
                    dp[i][j] += dp[i - 1][j - A[i - 1]];
                }
            }
        }
        return dp[n][sum + target];
    }
    //the same knapSack problems
    public int findTargetSumWays_1D(int[] A, int target) {
        int sum = Arrays.stream(A).sum();
        //we check (sum + S) % 2 == 1 is to exclude sum + S == odd number
        //sum means all positive choice for all elements, 
        //if target require some elements to be negative, then we will have either 0 or even number, because
        //the left numbers will be twice frequency
        
        //so this become a find some elements in array which sum can equals to a target this problem
        //subset sum
        if(target > sum || (sum + target) % 2 == 1)   return 0;
        return subsetSum(A, (sum + target) / 2);
    }

    private int subsetSum(int[] nums, int S){
        int[] dp = new int[S + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = S; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[S];
    }
}