package hatecode;
public class _1092ShortestCommonSupersequence {
/*
1092. Shortest Common Supersequence
943. Find the super string
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If multiple answers exist, you may return any of them.

(A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)

 

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
*/
    //thinking process: 
    
    //given two string s1 and s2, find shortest string of s1 and s2
    
    
    public String shortestCommonSupersequence(String str1, String str2) {
        //Part1 fill the longest common sequence table
        int[][] dp = new int[str1.length()+1][str2.length()+1];
        for(int i = 0;i<str1.length();i++){
            for(int j = 0;j<str2.length();j++){
                if(str1.charAt(i) == str2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                }else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1],dp[i+1][j]);
                }
            }
        }
        //Part2: use the table to get the ans
        StringBuilder sb = new StringBuilder();
        for(int i = str1.length()-1,j = str2.length()-1;i>=0 || j>=0;){
        //Case 1: either there is no char in str1 or str2, append char directly
            if(i < 0){
                sb.append(str2.charAt(j));
                j--;
                continue;
            }else if(j < 0){
                sb.append(str1.charAt(i));
                i--;
                continue;
            }
        //Case 2: if the value is the same compared with left or uppper cell, append corresponding char in str1 or str2
        // in longest common sequence, this means the char should be deleted, but in this problem, we need to append
            int val = dp[i+1][j+1];
            if(val == dp[i][j+1]){
                sb.append(str1.charAt(i));
                i--;
            }else if(val == dp[i+1][j]){
                sb.append(str2.charAt(j));
                j--;
        //Case 3 if the value is not the same compared with left or upper cell, append char and i--,j--
        //in longest common sequence, this means we find the common char
            }else {
                sb.append(str1.charAt(i));
                i--;
                j--;
            }
        }
        return sb.reverse().toString();
    }
    
    public String shortestCommonSupersequence_DP(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++){
            for (int j = 0; j <= n; j++){
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else if (str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int l = dp[m][n]; // Length of the ShortestSuperSequence
        char[] arr = new char[l];
        int i=m, j=n;
        while(i>0 && j>0) {
            /* If current character in str1 and str2 are same, then
             current character is part of shortest supersequence */
            if(str1.charAt(i-1) == str2.charAt(j-1)) {
                arr[--l] = str1.charAt(i-1);
                i--;j--;
            }else if(dp[i-1][j]<dp[i][j-1]) {
                arr[--l] = str1.charAt(i-1);
                i--;
            } else {
                arr[--l] = str2.charAt(j-1);
                j--;
            }
        }
        while (i > 0) {
            arr[--l] = str1.charAt(i-1);
            i--;
        }
        while (j > 0) {
            arr[--l] = str2.charAt(j-1);
            j--;
        }
        return new String(arr);
    }
}