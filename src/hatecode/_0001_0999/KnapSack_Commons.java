package hatecode._0001_0999;

import java.util.*;

public class KnapSack_Commons {
/*
 * Identify this problem as one of the categories below before continuing.

0/1 Knapsack
Unbounded Knapsack
Shortest Path (eg: Unique Paths I/II)
Fibonacci Sequence (eg: House Thief, Jump Game)
Longest Common Substring/Subsequeunce
494. Target Sum
 */
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

thinking about this way:
11 bags, it can hold 1,2...11 weight objects, so we if bag 8 already have its max posssible 
combination, we just use it. 
as we loop on the numbers, because of the number distribution, we can only have some  bags can be 
filled.

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
    
    public static int backPackI_2DDP(int[] nums, int w) {
        int n = nums.length;
        int[][] dp = new int[n+1][w+1];
        dp[0][0] = 0;
        //also we should initialize dp[i][0] = 0. dp[0][i] = 0
        //but they should be 0 and java initialized value is 0, so we skip
        for(int i = 1; i<=n; i++) {
            for(int j = 0; j<=w; j++) {
                if (j >= nums[i-1]) {
                    dp[i][j] = Math.max(dp[i-1][j - nums[i-1]] + nums[i-1], dp[i-1][j]);
                } else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][w];
    }
    //and then we can see dp[i+1] only rely on previous row, so we can use one dimension to 
    //reduce the space complexity
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
    public static int backPackII(int[] nums, int V[], int w) {
        int[] dp = new int[w+1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = w; j > 0; j--) {
                if (j >= nums[i]) dp[j] = Math.max(dp[j], dp[j-nums[i]]+V[i]);
            }
        }
        return dp[w];
    }
    //this is more space solution, but need to understand how we compress the space
    public static int backPackII_2DDP(int[] nums, int[] V, int w) {
        if (nums == null || nums.length < 1) return 0;
        
        int n = nums.length;
        //dp[i][j] means  i items weight <=j , max weight the first item can reach
        //note we have m + 1 len, that's why i - 1
        //dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-nums[i-1]] + nums[i-1]);

        int[][] dp =  new int[n + 1][w + 1];
        
        for(int i = 1; i<=n;i++) {
            for(int j = 0; j <=w;j++) {
                if (j < nums[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    //System.out.println(nums[i-1] + "--" + j);
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-nums[i-1]] + V[i-1]);
                }
            }
        }
        Arrays.stream(dp).forEach(e->System.out.println(Arrays.toString(e)));
        return dp[n][w];
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
    public static int backPackIII(int[] nums, int[] V, int w) {
        int[] dp = new int[w+1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= w; j++) {
                if (j >= nums[i]) dp[j] = Math.max(dp[j], dp[j-nums[i]]+V[i]);
            }
        }
        return dp[w];
    }
    
    public static int backPackIII_2DDP(int[] nums, int[] V, int w) {
        int n = nums.length;
        int[][] dp = new int[n+1][w+1];
        dp[0][0] = 0;
        
        for(int i =1; i<=n; i++) {
            for(int j = 0; j <=w;j++) {
                if (j < nums[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    //note here in max expressions, row index is i, not i-1 which means 
                    //it is aggregated sum
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j- nums[i-1]] + V[i-1]);
                }
            }
        }
        return dp[n][w];
    }
/*
backPackIV 重复选择+唯一排列+装满可能性总数， 唯一排列就是组合的意思
Given n items with size nums[i] which an integer array and all 
positive numbers, no duplicates. An integer target denotes the size of a 
backpack. Find the number of possible combinations to fill the backpack.

Example
Given candidate items [2,3,6,7] and target 7,
A solution set is:
[7]
[2, 2, 3]
return 2

LC 518. coin change II is this type of question
 */
    public static int backPackIV(int[] nums, int w) {
        int[] dp = new int[w+1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= w; j++) {
                if (nums[i] == j) dp[j]++;
                else if (nums[i] < j) dp[j] += dp[j-nums[i]];
            }
        }
        return dp[w];
    }
    
    public static int backPackIV_2DDP(int[] nums, int w) {
        int n = nums.length;
        int[][] dp = new int[n+1][w+1];
        dp[0][0] = 1; 
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 0; j <= w; j++) {
                if (j < nums[i-1]) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] + dp[i][j-nums[i-1]];
            }
        }
        return dp[n][w];
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
        public static int backPackV_2DDP(int[] nums, int w) {
            if (nums == null || nums.length < 1 || w < 1) return 0;
            int n = nums.length;
            int[][] dp = new int[n + 1][w + 1];
            dp[0][0]  = 1;
            for(int i= 1; i <=n; i++) {
                dp[i][0] = 1;
                for(int j = 0; j <= w; j++) {
                    if (j < nums[i-1]) dp[i][j] = dp[i-1][j];
                    else dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
                }
            }
            return dp[n][w];
        }

    
    
    
    
    //BackPackVI Problem 重复选择+不同排列+装满可能性总数, 不同排列 意思就是排列， 顺序相关
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
/*
for (int i = 0; i < nums.length; ++i){
    for (int j = target; j >= nums[i]; --j){
        f[j] += f[j - nums[i]];
    }
}
从target循环到nums[i]是因为每个物品只能使用一次，倒序循环不会影响之后的操作。
比如对于一个体积为5的物品 target=10
那么j循环就是f[10]+=f[10-5];f[9]+=f[9-5];f[8]+=f[8-5];f[7]+=f[7-5];f[6]+=f[6-5];f[5]+=f[5-5] ;
在每种可能的情况下放物品时，都是基于这个物品还没有放进去的情况，只有倒序循环才能满足条件，如果正序循环：
那么5次操作分别是f[5]+=f[5-5];f[6]+=f[6-5];f[7]+=f[7-5];f[8]+=f[8-5];f[9]+=f[9-5];f[10]+=f[10-5]
在计算f[10]的时候，f[5]在之前已经计算过了，并且是由f[0]得到的，这个容量为10的背包包含了装了两个体积为5的物品，是不符合题意的
 */
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
/*
可以看到最大的不同是，考虑组合次序不同是不同的方案的话是先迭代背包容量(空间)，而考虑组合次序不同是
同一种方案先迭代物品。 这是因为比如容量
大小为4的背包，[1, 1,2]和[2, 1, 1]是不同的方案，容量都为4，所以要先迭代容量，找出所有容量为4的物品组合。
http://www.yikanggao.com/blog/2017/06/%E8%83%8C%E5%8C%85%E9%97%AE%E9%A2%98%E7%BB%86%E8%8A%82%E6%8E%A2%E6%9E%90.html
 */
    //TODO：this code has some errors, like nums={1}, w=4, it would return 4,  should be 1
    //for nums=[3,4,5,6,7], w = 1, should be 0 but it returned 1
    public static int backPackVI_2DDP(int[] nums, int w) {
        if (nums == null || nums.length < 1 || w < 1) return 0;
        int n = nums.length;
        int[][] dp = new int[n + 1][w + 1];
        dp[0][0]  = 1;
        for(int j= 1; j <=w; j++) {
            dp[0][j] = 1;
            for(int i = 1; i <= n; i++) {
                if (j < nums[i-1]) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] + dp[i][j-nums[i-1]];
            }
        }
        return dp[n][w];
    }
   
    //1D array have some tricky part, summary as below:
    /*
     * 1. numbers can be repeated use or not:
     *  a. if not repeated, then num in outter loop, target is internal loop starting
     *      with w, -- as below: 
     *      for (int a: A) {
     *          for(int w= W; w>=0; w--) {...}
     *      } 
     *  b. if repeated, then num in outter loop, target is internal loop with start from 0; 
     *  
     * 2. if order of the elements needs to consider, like [1,2,1] and 
     *     [2,1,1] are different answers, then target is outter, numbers are inner 
     */
    
    public static void main(String[] args) {
        int[] in = {3,5,2,7};
        System.out.println(String.format("backPackI 单次选择 去找最大重量, input = %s, output=%s", Arrays.toString(in), backPackI(in, 11)));
        System.out.println(String.format("backPackI_Array 单次选择 去找最大重量, input = %s, output=%s", Arrays.toString(in), backPackI_2DDP(in, 11)));
        System.out.println("backPackII result should be 8, nums= {2,5,3,7}, v = {1,5,2,4}, w= 10, result =" + backPackII(new int[] {2,5,3,7}, new int[] {1,5,2,4}, 10));
        System.out.println("backPackII_2DDP result should be 8,  nums= {2,5,3,7}, v = {1,5,2,4}, w= 10,  result = " + backPackII_2DDP(new int[] {2,5,3,7}, new int[] {1,5,2,4}, 10));
        System.out.println("backPackIII result should be 15," + backPackIII(new int[] {2,3,5,7}, new int[] {1,5,2,4}, 10));
        System.out.println("backPackIII_2DDP result should be 15," + backPackIII_2DDP(new int[] {2,3,5,7}, new int[] {1,5,2,4}, 10));
        System.out.println("重复选择+唯一排列+装满可能性总数 backPackIV result should be 2, nums ={2,2,3}, w= 7, res =" +  backPackIV(new int[] {2,2,3}, 7));
        System.out.println("重复选择+唯一排列+装满可能性总数 backPackIV_2DDP result should be 2,nums ={2,2,3}, w= 7, res = " +  backPackIV_2DDP(new int[] {2,2,3}, 7));
        System.out.println("单次选择+装满可能性总数 backPackV result should be 2," +  backPackV(new int[] {1,1,3,3,7}, 7));
        System.out.println("单次选择+装满可能性总数 backPackV_2DDP result should be 2," + backPackV_2DDP(new int[] {1,1,3,3,7}, 7));
        System.out.println("重复选择+不同排列+装满可能性总数 backPackVI result should be 6," + backPackVI(new int[] {1}, 4));
        System.out.println("重复选择+不同排列+装满可能性总数 backPackVI_2DDP result should be 6," + backPackVI_2DDP(new int[] {1}, 4));
    }
}
