package hatecode._0001_0999;

import java.util.stream.*;
public class _1000MinimumCostToMergeStones {
/*
1000. Minimum Cost to Merge Stones
There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.

A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.

Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.

 

Example 1:

Input: stones = [3,2,4,1], K = 2
Output: 20
*/
  
  //Let's first think this problem in a simple way, what if we can only merge 2 adjacent piles into one pile?  
    public int mergeStones2(int[] stones) {
       
        int n = stones.length;
        int max = Integer.MAX_VALUE;
        int[][] dp = new int[n + 1][n + 1];
        int[] prefix = new int[n + 1];
        
       
        IntStream.range(1, n+1).forEach(e->prefix[e] = prefix[e-1] + stones[e-1]);
        IntStream.range(1, n+1).forEach(e->dp[e][e] = 0);
      //Function: dp[i][j] = min(sum[i][j] + dp[i][k] + dp[k + 1][j]) (i <= k < j)
        for(int l = 2; l <=n;l++) {
            for(int i =1;i <=n- l +1; i++) {
                int j = i + l - 1;
                dp[i][j] = max;
                int sum = prefix[j] - prefix[i-1];
                for(int k =i; k< j;k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + sum);
                }
            }
        }
        return dp[1][n];
        
    }
    //thinking process: 
    //given [3,2,4,1], K = 2, once a time, we have to mrege two numbers in array, 
    //[5,4,1]->[5,5]->20, each merge, the sum is the cost, so find the min cost
    //dp[i][j][1] = dp[i][j][K] + sum[i][j] (dp[i][j][K] != max)
    //dp[i][j][k] = min(dp[i][t][k - 1] + dp[t + 1][j][1]) (t ∈ [i, j) && dp[i][t][k - 1] != max && dp[t+1][j][1] != max && k ∈ [2, K])

    //Init: dp[i][i][1] = 0 (Already a pile) others = max
    
    //O(K * n^3), like burst bolloons
    //so above is K = 2, 
    public int mergeStones(int[] stones, int K) {
        if (stones == null || stones.length < 1 || (stones.length - 1) % (K - 1) != 0)
            return -1;
        
        int n = stones.length;
        int[] prefixSum = new int[n + 1];
        IntStream.range(1, n+1).forEach(e->prefixSum[e] = prefixSum[e - 1] + stones[e - 1]);
        
        //here cannot be Int.MAX becz later we have d[i] + dp[j] it would overflow
        int max = 99999999;
        
        int[][][] dp = new int[n + 1][n + 1][K + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= K; k++) {
                    dp[i][j][k] = max;
                }
            }
        }
        
        IntStream.range(1, n+1).forEach(e->dp[e][e][1] = 0);
        //here is the meat, l means window
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                for (int k = 2; k <= K; k++) {
                    for (int t = i; t < j; t++) {
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][t][k - 1] + dp[t + 1][j][1]);
                    }
                }
                dp[i][j][1] = dp[i][j][K] + prefixSum[j] - prefixSum[i - 1];   
            }
        }

        return dp[1][n][1];
    }
    
    
    int[][][] dp;
    int max = 99999999;
    int K;
    
    public int mergeStones_TopDown(int[] stones, int K) {
        this.K = K;
        int len = stones.length;
        if ((len - 1) % (K - 1) != 0) {
            return -1;
        }
        dp = new int[len + 1][len + 1][K + 1];
        int[] prefixSum = new int[len + 1];
        
        int i;
        for (i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i - 1];
        }
        
        return getResult(prefixSum, 1, len, 1);
    }
    
    private int getResult(int[] prefixSum, int left, int right, int piles) {
        if (dp[left][right][piles] != 0) {
            return dp[left][right][piles];
        }
        int res = max;
        int t;
        if (left == right) {
            res = (piles == 1) ? 0 : max;
        }
        else {
            if (piles == 1) {
                res = getResult(prefixSum, left, right, K) + prefixSum[right] - prefixSum[left - 1]; 
            }
            else {
                for (t = left; t < right; t++) {
                    res = Math.min(res, getResult(prefixSum, left, t, piles - 1) + getResult(prefixSum, t + 1, right, 1));
                }
            }
        }
        dp[left][right][piles] = res;
        return res;
    }
}