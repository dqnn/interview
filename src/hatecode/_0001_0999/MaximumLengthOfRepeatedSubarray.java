package hatecode._0001_0999;
import java.util.*;
public class MaximumLengthOfRepeatedSubarray {
/*
718. Maximum Length of Repeated Subarray
Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].
*/
    //standard LC substring DP
    public int findLength_DP(int[] A, int[] B) {
        if (A == null || B == null ||A.length < 1 || B.length < 1) return 0;
        
        int m = A.length, n =B.length;
        int[][] dp = new int[m+1][n+1];
        int res = Integer.MIN_VALUE;
        for(int i =1; i<=m; i++) {
            for(int j =1; j<=n;j++) {
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res == Integer.MIN_VALUE ? 0 : res;
    }
    
    public int findLength(int[] A, int[] B) {
        int[] dp  = new int[A.length+1];
        int max = 0;
        for(int i = 0;i<B.length;i++){
            for(int j = A.length - 1;j>=0;j--){
                if(A[j]==B[i]){
                    dp[j+1]=dp[j]+1;
                    max = Math.max(max, dp[j+1]);

                } else dp[j+1]=0;
            }
        }
        return max;
    }
    
    public int findLength_LeastMEM(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) return -1;
        
        int ans = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < B.length; i++) {
            map.putIfAbsent(B[i], new ArrayList<>());
            map.get(B[i]).add(i);
        }
        
        for(int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                for(int j : map.get(A[i])) {
                    int offset = 0;
                    while(i + offset < A.length 
                         && j + offset < B.length
                         && A[i+offset] == B[j+offset]) {
                        offset++;
                    }
                    ans = Math.max(ans, offset);    
                }                         
            }
        }        
        return ans;
    }
    
    
}