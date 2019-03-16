package hatecode;
public class MinimumASCIIDeleteSumForTwoStrings {
/*
712. Minimum ASCII Delete Sum for Two Strings
Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

Example 1:
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
*/
    //interview friendly, 
    //"s1=sea, s2=eat", only delete from two strings, what's the minimum sum of to be equals of the deleted 
    //chars. 
    
    //first considering s1 ="", then it would required delete all s2, then think about for s2, so 
    //lets's dp[i][j] is minimal ascii sum for s1,0-i, s2, 0-j, then we can see two cases
    //if s1[i]=s2[j], then dp[i][j] = dp[i-1][j-1], if not, then we would delete s1[i] or s2[j],
    //we choose the min one, this is the same as edit distance
    public int minimumDeleteSum_2D(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < 1 || s2.length() < 1) return 0;
        
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n+1];
        dp[0][0]=0;
        for (int j = 1; j <= n; j++)
            dp[0][j] = dp[0][j-1]+s2.charAt(j-1);
        
        //we can embed this into first loop 
        for (int j = 1; j <= m; j++)
            dp[j][0] = dp[j-1][0]+s1.charAt(j-1);
        
        for(int i =1; i<=m;i++) {
            //dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
            for(int j =1; j<=n;j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j] + s1.charAt(i-1), dp[i][j-1] + s2.charAt(j-1));
                }
            }
        }
        return dp[m][n];
    }
    
    public int minimumDeleteSum(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < 1 || s2.length() < 1) return 0;
        
        int m = s1.length(), n = s2.length();
        int[] dp = new int[n + 1];
        for (int j = 1; j <= n; j++)
            dp[j] = dp[j-1]+s2.charAt(j-1);
        for (int i = 1; i <= m; i++) {
            //since it required i-1,j-1, so we need a variable to remember dp[i-1][j-1]
            int t1 = dp[0];
            dp[0] += s1.charAt(i-1);
            for (int j = 1; j <= n; j++) {
                int t2 = dp[j];
                dp[j] = s1.charAt(i-1) == s2.charAt(j-1)? t1:Math.min(dp[j]+s1.charAt(i-1), 
                                                                      dp[j-1]+s2.charAt(j-1));
                t1 = t2;
            }
        }
        return dp[n];
    }
    
    
}