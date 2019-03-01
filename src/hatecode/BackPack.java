package hatecode;

import java.util.*;

public class BackPack {

    //list of back pack problems and explain
/*
 * Backpack I Problem 单次选择 去找最大重量
If we have 4 items with size [2, 3, 5, 7], 
the backpack size is 11, we can select [2, 3, 5], 
so that the max size we can fill this backpack is 10. 
If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.

Challenge
O(n x m) time and O(m) memory.

O(n x m) memory is also acceptable if you do not know how to optimize memory.
if we want to output the numbers as a list, how? 
1. back tracking is doable but with high complexity.
2.

thiking about this way:
11 bags, it can hold 1,2...11 weight objects, so we if bag 8 already have its max posssible 
combination, we just use it. 

so every time, we use each nums[i] to be added in these 11 bags, so it just means 
for num[i], put it in each bag or not put in bag will make it heavier or not
 */
    //dp[i] = max{dp[i], dp[i - nums[j] + nums[j}, j = 0, ...nums.length - 1
    public static int backPackI(int[] nums, int w) {
        int[] dp = new int[w+1];
        int[][] m = new int[nums.length][w+1];
        for (int i = 0; i < nums.length; i++) {
            //reduce some iteration
            for (int j = w; j >= nums[i]; j--) {
                //if (j >= nums[i]) {
                   //if we do not print the path, just use this line is enough
                   //dp[j] = Math.max(dp[j], dp[j-nums[i]] + nums[i]);
                    
                   //this is to print all combinations
                    if (dp[j - nums[i]] + nums[i] > dp[j]) {
                        dp[j] = Math.max(dp[j], dp[j-nums[i]] + nums[i]);
                        m[i][j] = 1;
                    }
                //}
            }
        }
        //here is to print the path, general way
        int j = w;
        for(int i =nums.length - 1; i>=0; i--) {
                if (m[i][j] == 1)  {
                    System.out.println("picked: " + nums[i]);
                    j -= nums[i];
            }
        }
        return dp[w];
    }
/*
backPackII Problem 单次选择+最大价值
Given n items with size nums[i] and value V[i], and a backpack with size w. 
What's the maximum value can you put into the backpack?
Given  size = [2, 3, 5, 7] 
values = [1, 5, 2, 4], w = 10. 
return 9.
O(n x m) memory is acceptable, can you do it in O(m) memory?
 */
//BackPack I基本一致。依然是以背包空间为限制条件，所不同的是dp[j]取的是价值较大值，
    //而非体积较大值。所以只要把dp[j-A[i]]+A[i]换成dp[j-A[i]]+V[i]就可以了。
    public static int backPackII(int[] nums, int V[], int m) {
        int[] dp = new int[m+1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = m; j > 0; j--) {
                if (j >= nums[i]) dp[j] = Math.max(dp[j], dp[j-nums[i]]+V[i]);
            }
        }
        return dp[m];
    }
    //this is more space solution, but need to understand how we compress the space
    public int backPackII_2DDP(int[] nums, int m) {
        if (nums == null || nums.length < 1) return 0;
        
        int N = nums.length;
        //dp[i][j] means  i items weight <=j , max weight the first item can reach
        //note we have m + 1 len, that's why i - 1
        //dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-nums[i-1]] + nums[i-1]);
        
        //TODO: why we always use len =  m + 1,
        int[][] dp =  new int[N + 1][m + 1];
        
        for(int i = 1; i<=N;i++) {
            for(int j = 0; j <=m;j++) {
                if (j < nums[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    System.out.println(nums[i-1] + "--" + j);
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-nums[i-1]] + nums[i-1]);
                }
            }
        }
        Arrays.stream(dp).forEach(e->System.out.println(Arrays.toString(e)));
        return dp[N][m];
    }
/*
backPackIII 重复选择+最大价值
Given n kind of items with size Ai and value Vi( each item has an infinite number available) 
and a backpack with size m. 
What's the maximum value can you put into the backpack?
Example:
nums = [2, 3, 5, 7] values =[1, 5, 2, 4], 
m =  10. return 15
 */
    public static int backPackIII(int[] A, int[] V, int m) {
        int[] dp = new int[m+1];
        for (int i = 0; i < A.length; i++) {
            for (int j = 1; j <= m; j++) {
                if (j >= A[i]) dp[j] = Math.max(dp[j], dp[j-A[i]]+V[i]);
            }
        }
        return dp[m];
    }
/*
backPackIV 重复选择+唯一排列+装满可能性总数
Given n items with size nums[i] which an integer array and all 
positive numbers, no duplicates. An integer target denotes the size of a 
backpack. Find the number of possible fill the backpack.

Each item may be chosen unlimited number of times
target = 7
nums = [2, 2, 3]
return 2
 */
    public static int backPackIV(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i] == j) dp[j]++;
                else if (nums[i] < j) dp[j] += dp[j-nums[i]];
            }
        }
        return dp[target];
    }
    /*
    backPackV Problem 单次选择+装满可能性总数
    Given n items with size nums[i] which an integer array and 
    all positive numbers. An integer target denotes the size of a backpack. 
    Find the number of possible fill the backpack.

    Each item may only be used once

    Example
    Given candidate items [1,2,3,3,7] and target 7,

    A solution set is:

    [7]
    [1, 3, 3]
    return 2
         */
        //space compresssion one
        public static int backPackV(int[] nums, int target) {
            if (nums == null || nums.length < 1 || target < 1) return 0;
            int[] dp = new int[target + 1];
            dp[0]  = 1;
            for(int i = 1; i <= nums.length; i++) {
                for(int j = target; j >= nums[i-1]; j--) {
                    dp[j] += dp[j - nums[i-1]];
                }
            }
            return dp[target];
        }
        //dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]]
        //first dp[i-1][j] means not using the i-1 item, means we always reach j
        // dp[i-1][j-nums[i-1]] means i-1 items with last i-1 items, we can reach j, using i-1
        //dp[1][1],dp[1][2],dp[1][3] to say how many ways for 1 to get 1 ,2 3 weight
        //dp[N][0],dp[N][1]...dp[N][target] to say how many ways for N to form target 0,,target
        //
        public static int backPackV2(int[] nums, int target) {
            if (nums == null || nums.length < 1 || target < 1) return 0;
            int[][] dp = new int[nums.length + 1][target + 1];
            dp[0][0]  = 1;
            for(int i= 1; i <=nums.length; i++) {
                dp[i][0] = 1;
                for(int j = 1; j <= target; j++) {
                    dp[i][j] = dp[i-1][j] + (j >= nums[i-1] ? dp[i-1][j-nums[i-1]] : 0);
                }
            }
            return dp[nums.length][target];
        }

    
    
    
    
    //BackPackVI Problem 重复选择+不同排列+装满可能性总数
/*
Given an integer array nums with all positive numbers and no duplicates, 
find the number of possible combinations that add up to a positive integer target.
Examples
Given nums = [1, 2, 4], target = 4
The possible combination ways are:

[1, 1, 1, 1]
[1, 1, 2]
[1, 2, 1]
[2, 1, 1]
[2, 2]
[4]
return 6
 */
    //dp[i] = dp[i-nums[0]] + dp[i-nums[1]] + ... + dp[i-nums[i-1]]
    //coin change 1 and 2 belong to this
    public static int backPackVI(int[] nums, int target) {
        if (nums == null || nums.length < 1 || target < 1) return 0;
        int[] dp = new int[target + 1];
        dp[0]  = 1;
        for(int i = 1; i <= target; i++) {
            for(int num : nums) {
                if (i >= num) dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
   
    
    public static void main(String[] args) {
        int[] in = {2,3,5,7};
        System.out.println(String.format("backPackI 单次选择 去找最大重量, input = %s, output=%s", Arrays.toString(in), backPackI(in, 11)));
        System.out.println("backPackII result should be 9," + backPackII(new int[] {2,5,3,7}, new int[] {1,5,2,4}, 10));
        System.out.println("backPackIII result should be 15," + backPackIII(new int[] {2,3,5,7}, new int[] {1,5,2,4}, 10));
        System.out.println("backPackIV result should be 2," +  backPackIV(new int[] {2,2,3}, 7));
        System.out.println("backPackV result should be 2," +  backPackV(new int[] {1,2,3,3,7}, 7));
        System.out.println("backPackV2 result should be 2," + backPackV2(new int[] {1,2,3,3,7}, 7));
        System.out.println("backPackVI result should be 6," + backPackVI(new int[] {1,2,4}, 4));
    }
}
