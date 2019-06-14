package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestPalindromicSubsequence
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : TODO
 */
public class LongestPalindromicSubsequence {
    /**
     * 516. Longest Palindromic Subsequence
     * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

     Example 1:githu
     Input:

     "bbbab"
     Output:
     4
     One possible longest palindromic subsequence is "bbbb".
     Example 2:
     Input:

     "cbbd"
     Output:
     2
     One possible longest palindromic subsequence is "bb".

     time : O(n^2)
     space : O(n^2)
     * @param s
     * @return
     */
    /*the longest palindromic subsequence's length of substring(i, j), 
    here i, j represent left, right indexes in the string
    State transition:
        dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
        otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
        Initialization: dp[i][i] = 1
        */
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        //i from last to 0, 
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            // j after i but point to the end
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }
    
    public int longestPalindromeSubseq2(String s) {
        return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);   
    }
    
    public int helper(String s, int start, int end, Integer[][] memo) {
        if (memo[start][end] != null) {
            return memo[start][end];
        }
        // so start exceed the end index, this should not exist
        if (start > end) {
            return 0;
        }
        // means only 1 char,so we return 1
        if (start == end) {
            return 1;
        }
        // the same transiation equations
        if (s.charAt(start) == s.charAt(end)) {
            memo[start][end] = helper(s, start+1, end - 1, memo) + 2;
        } else {
            memo[start][end] = Math.max(helper(s, start+1, end, memo), helper(s, start, end - 1, memo));
        }
        
        return memo[start][end];
    }
    
    //another interview friendly solution
    //boolean dp[i][j] is to state whether substring(i,j) is palindrom or not, 
    //
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start=0, end=0;
        for(int j =0; j< n; j++) {
            for(int i = 0; i <= j;i++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - i<=2) || dp[i+1][j-1]);
                if (dp[i][j] && (j-i+1>(end - start + 1))) {
                    start = i;
                    end = j;
                }
            }
        }
        return n == 0 ? "" : s.substring(start, end + 1);
    }
    
}
