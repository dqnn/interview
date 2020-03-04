package hatecode._0001_0999;
import java.util.*;
public class _474OnesAndZeroes {
/*
474. Ones and Zeroes
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.
Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
*/
    public int findMaxForm_3D(String[] strs, int m, int n) {
    int l = strs.length;
    int[][][] dp = new int[l+1][m+1][n+1];
    
    for (int i = 0; i < l+1; i++) {
        int count0 = 0, count1=0;
        if (i > 0)
        for(char ch : strs[i-1].toCharArray()) {
            if (ch == '0') count0++;
            else if (ch == '1') count1++;
        }
        
        for (int j = 0; j < m+1; j++) {
            for (int k = 0; k < n+1; k++) {
                if (i == 0) {
                    dp[i][j][k] = 0;
                } else if (j>=count0 && k>=count1) {
                    dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i-1][j-count0][k-count1]+1);
                } else {
                    dp[i][j][k] = dp[i-1][j][k];
                }
            }
        }
    }
    
    return dp[l][m][n];
   }
    
    // dp[i][j][k] means the maximum number of strings we can get from the first i
    // argument strs using limited j number of '0's and k number of '1's.
    // For dp[i][j][k], we can get it by fetching the current string i or discarding
    // the current string, which would result in dp[i][j][k] =
    // dp[i-1][j-numOfZero(strs[i])][i-numOfOnes(strs[i])] and dp[i][j][k] =
    // dp[i-1][j][k]; We only need to treat the larger one in it as the largest
    // number for dp[i][j][k].
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int count0 = 0, count1 = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '0')
                    count0++;
                else if (ch == '1')
                    count1++;
            }
            for (int j = m; j >= count0; j--) {
                for (int k = n; k >= count1; k--) {
                    if (j >= count0 && k >= count1) {
                        dp[j][k] = Math.max(dp[j][k], dp[j - count0][k - count1] + 1);
                    } else {
                        // this can be ignored
                        // dp[j][k] = dp[j][k];
                    }
                }
            }
        }

        return dp[m][n];
    }
    
 
    
}