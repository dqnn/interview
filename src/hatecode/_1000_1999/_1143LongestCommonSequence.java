package hatecode._1000_1999;

import java.util.regex.Matcher;


public class _1143LongestCommonSequence {
/*
 * 1143. Longest Common Subsequence
 * Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

 

If there is no common subsequence, return 0.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
given two strings, find the LCS.
example:
"ABCD" and "EDCA" --> LCS = A or C or D return 1
"ABCD" and "EACB"--> LCS = AB return 2
 */
    
    public static int LCS(String AA, String BB) {
        char[] A = AA.toCharArray();
        char[] B = BB.toCharArray();
        int m = A.length, n = B.length;
        int[][] dp = new int[m +1][n+1];
        
        int[][] pi = new int[m+1][n+1];
        int i = 0, j =0;
        for(i = 0;i <=m;i++) {
            for(j = 0;j<=n;j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                
                if (dp[i][j] == dp[i-1][j]) pi[i][j]= 0;
                else pi[i][j] = 1;
                
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = Math.max(dp[i-1][j-1] + 1, dp[i][j]);
                    if (dp[i][j] == dp[i-1][j-1] + 1) {
                        pi[i][j] = 2;
                    }
                }
            }
        }
        
        char[] res = new char[dp[m][n]];
        i = m; j= n;
        int p = dp[m][n] - 1;
        while(i >0 && j > 0) {
            if (pi[i][j] == 0) --i;
            else if (pi[i][j] == 1) --j;
            else {
                res[p] = A[i-1];
                --p;
                --i;
                --j;
            }
        }
        System.out.println("longest Common sequence: ");
        for(i = 0; i < dp[m][n]; i++) {
            System.out.println(res[i]);
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(LCS("ABCD", "EACB"));
    }

}
