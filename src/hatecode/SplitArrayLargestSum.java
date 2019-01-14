package hatecode;
public class SplitArrayLargestSum {
/*
410. Split Array Largest Sum
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

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
        return dfs(0, m, nums, presum, visited);
    }
    //m means groups
    private int dfs(int start, int m, int[] nums, int[] presum, int[][] visited) {
        //like edge case, only 1 group
        if (m == 1) {
            return presum[nums.length] - presum[start];
        }
        
        if (visited[start][m] != 0) {
            return visited[start][m];
        }
        
        int maxSum = Integer.MAX_VALUE;
        //this is templates for groups and nums to get max for subarray problems
        for (int i = start; i < nums.length-1; i++) {
            //left means first group sum
            int left = presum[i+1] - presum[start];
            //this will be recursive calc to each group
            int rightIntervalMax = dfs(i+1, m-1, nums, presum, visited);
            //this would pick max sum for each group
            maxSum = Math.min(maxSum, Math.max(left, rightIntervalMax));
            
        }
        
        visited[start][m] = maxSum;
        return maxSum;
    }
    
    //binary search, 
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
            long mid = (l + r)/ 2;
            if (valid(mid, nums, m)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int)l;
    }
    public boolean valid(long target, int[] nums, int m) {
        int count = 1;
        long total = 0;
        for(int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }
    
    //DP, 
    /*
    dp[s,j] is the solution for splitting subarray n[j]...n[L-1] into s parts.
     dp[s+1,i] = min{ max(dp[s,j], n[i]+...+n[j-1]) }, i+1 <= j <= L-s
    */
    public int splitArray(int[] nums, int m) {
        int L = nums.length;
        int[] S = new int[L + 1];
        S[0] = 0;
        for (int i = 0; i < L; i++)
            S[i + 1] = S[i] + nums[i];

        int[] dp = new int[L];
        for (int i = 0; i < L; i++)
            dp[i] = S[L] - S[i];

        for (int s = 1; s < m; s++) {
            for (int i = 0; i < L - s; i++) {
                dp[i] = Integer.MAX_VALUE;
                for (int j = i + 1; j <= L - s; j++) {
                    int t = Math.max(dp[j], S[j] - S[i]);
                    if (t <= dp[i])
                        dp[i] = t;
                    else
                        break;
                }
            }
        }

        return dp[0];
    }
}