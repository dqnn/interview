package hatecode;
public class DeleteOperationForTwoStrings {
/*
583. Delete Operation for Two Strings
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
*/
    
    //we first find the Longest common sequence, then to make the same so each should delete others
    public int minDistance_LCS(String w1, String w2) {
        if (w1 == null && w2 == null || w1.equals(w2)) return 0;
        
        int r = w1.length(), c =w2.length();
        int[][] dp = new int[r + 1][c + 1];

        for(int i = 1; i<= r; i++) {
            for(int j = 1; j<= c; j++) {
                if(w1.charAt(i-1) == w2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return w1.length() + w2.length() - 2* dp[r][c];
    }
    
    // straight forward DP solution, same as  a lot DP
    public int minDistance(String w1, String w2) {
        if (w1 == null && w2 == null || w1.equals(w2)) return 0;
        
        int r = w1.length(), c =w2.length();
        int[][] dp = new int[r + 1][c + 1];
        
        for(int i = 1; i <= r; i++) {
            dp[i][0] = dp[i-1][0] + 1;
        }
        
        for(int i = 1; i <= c; i++) {
            dp[0][i] = dp[0][i-1] + 1;
        }

        for(int i = 1; i<= r; i++) {
            for(int j = 1; j<= c; j++) {
                if(w1.charAt(i-1) == w2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp[r][c];
    }
}