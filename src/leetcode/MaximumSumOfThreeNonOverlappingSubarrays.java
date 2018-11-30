package leetcode;
public class MaximumSumOfThreeNonOverlappingSubarrays {
/*
689. Maximum Sum of 3 Non-Overlapping Subarrays
The question asks for three non-overlapping intervals with maximum sum of all 3 intervals. 
If the middle interval is [i, i+k-1], where k <= i <= n-2k,
 the left interval has to be in subrange [0, i-1], 
 and the right interval is from subrange [i+k, n-1].

So the following solution is based on DP.

posLeft[i] is the starting index for the left interval in range [0, i];
posRight[i] is the starting index for the right interval in range [i, n-1]; 
Then we test every possible starting index of middle interval, i.e. k <= i <= n-2k, 
and we can get the corresponding left and right max sum intervals easily from DP. 
And the run time is O(n).

Caution. In order to get lexicographical smallest order, when there are two intervals 
with equal max sum, always select the left most one. So in the code, 
the if condition is ">= tot" for right interval due to backward searching, 
and "> tot" for left interval. Thanks to @lee215 for pointing this out!
*/
    //thinking process: 
    //the problem is to find 3 non-overlap subarrays, the length is 2 and totally 6 digits could get
    //max sum, and return the smallest lexicographical ones
    
    //
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length, maxsum = 0;
        int[] sum = new int[n+1], posLeft = new int[n], posRight = new int[n], ans = new int[3];
        //sum starts from 1, not 0
        for (int i = 0; i < n; i++) sum[i+1] = sum[i]+nums[i];
        // DP for starting index of the left max sum interval
        //note: sum[k] - sum[0] means sum(nums[0] + ..nums[k-1] - 0) = sum(nums[0]+..nums[k-1])
        for (int i = k, tot = sum[k]-sum[0]; i < n; i++) {
            if (sum[i+1]-sum[i+1-k] > tot) {
                posLeft[i] = i+1-k;
                tot = sum[i+1]-sum[i+1-k];
            }
            else
                posLeft[i] = posLeft[i-1];
        }
        // DP for starting index of the right max sum interval
       // caution: the condition is ">= tot" for right interval, and "> tot" for left interval
        posRight[n-k] = n-k;
      //note: sum[n] - sum[n-k] means sum(nums[0] + ..nums[n-1] - nums[0]...nums[n-k-1])
        // = sum(nums[n-k] + nums[n-1])
        for (int i = n-k-1, tot = sum[n]-sum[n-k]; i >= 0; i--) {
            if (sum[i+k]-sum[i] >= tot) {
                posRight[i] = i;
                tot = sum[i+k]-sum[i];
            }
            else
                posRight[i] = posRight[i+1];
        }
        // test all possible middle interval
        for (int i = k; i <= n-2*k; i++) {
            int l = posLeft[i-1], r = posRight[i+k];
            int tot = (sum[i+k]-sum[i]) + (sum[l+k]-sum[l]) + (sum[r+k]-sum[r]);
            if (tot > maxsum) {
                maxsum = tot;
                ans[0] = l; ans[1] = i; ans[2] = r;
            }
        }
        return ans;
    }
    /*
 similar to that buy and sell stock problem.

dp[i][j] stands for in i th sum, the max non-overlap sum we can have from 0 to j
id[i][j] stands for in i th sum, the first starting index for that sum.
     */
    
    public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
        int[][] dp = new int[4][nums.length + 1];
        int sum = 0;
        int[] accu = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            accu[i] = sum;
        }
        int[][] id = new int[4][nums.length + 1];
        int max = 0, inId = 0;
        for(int i = 1; i < 4; i++) {
            for(int j = k-1 ; j < nums.length; j++) {
                int tmpmax = j - k < 0 ? accu[j] : accu[j] - accu[j-k] + dp[i-1][j-k];
                if(j - k >= 0) {
                    dp[i][j] = dp[i][j-1];
                    id[i][j] = id[i][j-1];
                }
                if(j > 0 && tmpmax > dp[i][j-1]) {
                    dp[i][j] = tmpmax;
                    id[i][j] = j-k+1;
                }
            }
        }
        int[] res = new int[3];
        res[2] = id[3][nums.length-1];
        res[1] = id[2][res[2] - 1];
        res[0] = id[1][res[1] - 1];        
        return res;
    }
    // standard solution
/*

 */
        public int[] maxSumOfThreeSubarrays3(int[] nums, int K) {
            //W is an array of sums of windows
            //note: W is sliding window size = k
            int[] W = new int[nums.length - K + 1];
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                //nums[i-k] needs to be removed from sum since sliding window
                if (i >= K) sum -= nums[i-K];
                // from k-1, assign the value to w, w[0] = nums[0] ... nums[k-1]
                //w[1] = nums[0]+..nums[k] - nums[0] = nums[1]+..nums[k]
                if (i >= K-1) W[i-K+1] = sum;
            }
            //scan from left to right,left will become left increase array, 
            int[] left = new int[W.length];
            int best = 0;
            for (int i = 0; i < W.length; i++) {
                if (W[i] > W[best]) best = i;
                left[i] = best;
            }
          
            //scan from right, and to record the results
            int[] right = new int[W.length];
            best = W.length - 1;
            for (int i = W.length - 1; i >= 0; i--) {
                if (W[i] >= W[best]) best = i;
                right[i] = best;
            }
             
            int[] ans = new int[]{-1, -1, -1};
          //merge the results, so i and k are all largest value if from left or right
            //so we just iterator 
            for (int j = K; j < W.length - K; j++) {
                int i = left[j - K], k = right[j + K];
                if (ans[0] == -1 || W[i] + W[j] + W[k] >
                        W[ans[0]] + W[ans[1]] + W[ans[2]]) {
                    ans[0] = i;
                    ans[1] = j;
                    ans[2] = k;
                }
            }
            return ans;
        }
}