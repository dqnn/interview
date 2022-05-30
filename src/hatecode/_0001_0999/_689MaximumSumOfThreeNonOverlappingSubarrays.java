package hatecode._0001_0999;
public class _689MaximumSumOfThreeNonOverlappingSubarrays {
/*
689. Maximum Sum of 3 Non-Overlapping Subarrays
In a given array nums of positive integers, find three non-overlapping 
subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each 
interval (0-indexed). If there are multiple answers, return the lexicographically 
smallest one.

Example:
Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.


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
        int[] sum = new int[n+1], posLeft = new int[n], posRight = new int[n], res = new int[3];
        //sum starts from 1, not 0
        for (int i = 0; i < n; i++) sum[i+1] = sum[i]+nums[i];
        // DP for starting index of the left max sum interval
        //note: sum[k] - sum[0] means sum(nums[0] + ..nums[k-1] - 0) = sum(nums[0]+..nums[k-1])
        for (int i = k, tot = sum[k]-sum[0]; i < n; i++) {
            if (sum[i+1]-sum[i+1-k] > tot) {
                posLeft[i] = i+1-k;
                tot = sum[i+1]-sum[i+1-k];
            } else posLeft[i] = posLeft[i-1];
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
                res[0] = l; res[1] = i; res[2] = r;
            }
        }
        return res;
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

 */     //interview friendly
/*
Trace details:

nums=[1,2,1,2,6,7,5,1], k=2

preSum = [1, 3, 4, 6, 12, 19, 24, 25]

leftMaxPos = [0, 0, 0, 0, 3, 4, 4, 4]

rightMaxPos = [4, 4, 4, 4, 4, 5, 6, 0]

for i=2, i<=4
i=2
    0 1 2 3 4 5 6 7
    - - ^ - - - - -

    curLeftMaxPos = leftMaxPos[2-1]  = 0
    curRightMaxPos= rightMaxPos[2+2] = 4

    cur3Sum = 3 + 3 + 13 = 19
    maxSum = 19
    results[0] = 0;
    results[1] = 2;
    results[2] = 4; 

i=3     
    0 1 2 3 4 5 6 7
    - - - ^ - - - -

    curLeftMaxPos = leftMaxPos[3-1]  = 0
    curRightMaxPos= rightMaxPos[2+2] = 5

    cur3Sum = 3 + 8 + 12 = 23
    maxSum = 23
    results[0] = 0;
    results[1] = 3;
    results[2] = 5; 

i=4 
    0 1 2 3 4 5 6 7
    - - - - ^ - - -

    curLeftMaxPos = leftMaxPos[4-1]  = 0
    curRightMaxPos= rightMaxPos[4+2] = 6

    cur3Sum = 3 + 13 + 6 = 22
    maxSum = 23 
 */
    public int[] maxSumOfThreeSubarrays_Non_DP(int[] nums, int k) {
        int[] result = new int[3]; 
        if(nums == null || nums.length == 0) {
            return result;
        }
        
        int n = nums.length;
        //preKSum[i] means i as last index, window = K sum
        int[] preKSum = new int[n];
        
        // k prefix
        preKSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preKSum[i] = preKSum[i - 1] + nums[i];
            //clever subtract
            if (i >= k) preKSum[i] -= nums[i - k];
        }
        
        // scan from left to right, starting from k - 1, because left[i] means the for k -1->i index, 
        //the max window sum size end index is left[i]
        int[] left = new int[n];
        int leftMaxIndex = k - 1;
        for (int i = k - 1; i < n; i++) {
            left[i] = preKSum[i] > preKSum[leftMaxIndex] ? i : leftMaxIndex;
            leftMaxIndex = left[i];
        }
        
     // scan from left to right, starting from n - 1, because right[i] means the for n-1-> k -1 index, 
        //the max window sum size end index is right[i]
        int[] right = new int[n];
        int rightMaxIndex = n - 1;
        for (int i = n - 1; i >= k - 1; i--) {
            right[i] = preKSum[i] >= preKSum[rightMaxIndex] ? i : rightMaxIndex;
            rightMaxIndex = right[i];
        }
        
        int max = Integer.MIN_VALUE;
        // scan all possible intervals, i means the middle index starting point, which means 
        //left at least have one, right at least have one, so 2k - 1 - k means the left point starting 
        //from k - 1
        for (int i = 2 * k - 1; i < n - k; i++) {
            int sum3 = preKSum[left[i - k]] + preKSum[i] + preKSum[right[i + k]];
            if (sum3 > max) {
                max = sum3;
                result[0] = left[i - k] - k + 1;
                result[1] = i - k + 1;
                result[2] = right[i + k] -k + 1;
            }
        }
        
        return result;
    }
    //this is used to solve the n segments as follow up, still not understand this. 
    //reference: 
    //https://www.jiuzhang.com/solution/maximum-sum-of-3-non-overlapping-subarrays/#tag-highlight
    public int[] helper(int[] A, int k, int seg) {
        int n = A.length;
        int[] presum = new int[n+1], sum = new int[n];
        for (int i = 1 ;i<= n ;i++ ) {
            presum[i] = presum[i-1]+A[i-1];
            if( i>=k) sum[i-1] = presum[i]-presum[i-k];
        }
        int[][] f = new int[n+1][2];
        int[][][] g = new int[n+1][2][seg];
        int cur = 0 , pre =0;
        for(int j = 1; j <= seg; j++){
            cur = 1-cur;    
            pre = 1-cur;
            for(int i = k*j ; i <= n; i++){
                boolean flag = i == k*j || f[i-k][pre]+sum[i-1]>f[i-1][cur];
                f[i][cur] = flag? f[i-k][pre]+sum[i-1]:f[i-1][cur];
                for(int p = 0; p<j-1; p++)
                    g[i][cur][p]= flag?g[i-k][pre][p]:g[i-1][cur][p];
                g[i][cur][j-1] = flag? i-k : g[i-1][cur][j-1];    
            }
        }
        return g[n][cur];
    }
}