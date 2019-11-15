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
    //thinking process: O(mn + m + n)/O(mn)
    
    //given two string s1 and s2, find shortest string of s1 and s2
    
    //so first need to understand how we calc LCS dp
    //LCS means longest common subsequence, so it is the longest same part between
    //s1 and s2, 
    
    //dp[i][j] means LCS for s1[0,i-1] and s2[0,j-1] 
    /*
     *       a  b  a  c
     *     0 0  0  0  0
     *   c 0 0  0  0  1
     *   a 0 1  1  1  1
     *   b 0 1  2  2  2
     *   
     *   so from above example we can see ab is LCS
     *   
     *          cond     result
     *   case1: i < 0,  s2[--i]  we choose s2,
     *   case2: j < 0,  s1[--j]  we choose s1
     *   case3: s1[i]==s2[j]:s1[--i],s2[--j]
     *   case4: dp[i-1][j] == dp[i][j] s1[--i],means s1[i] does not match, then we need insert into sb
     *   case5: dp[i][j-1] == dp[i][j] s2[--j],means s2[j] does not match, then insert into sb
     so be careful about the index, especially on case 1 and case 2
     */
    public String shortestCommonSupersequence(String s1, String s2) {
        //Part1 fill the longest common sequence table
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int i = 0;i<s1.length();i++){
            for(int j = 0;j<s2.length();j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                }else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1],dp[i+1][j]);
                }
            }
        }
        //Part2: use the table to get the res
        StringBuilder sb = new StringBuilder();
        for(int i = s1.length()-1,j = s2.length()-1;i>=0 || j>=0;){
        //Case 1: either there is no char in str1 or str2, append char directly
            if(i < 0){
                sb.append(s2.charAt(j));
                j--;
                continue;
        //Case 2
            }else if(j < 0){
                sb.append(s1.charAt(i));
                i--;
                continue;
            }
            
            int val = dp[i+1][j+1];
            //case 4
            if(val == dp[i][j+1]){
                sb.append(s1.charAt(i));
                i--;
            //case 5
            } else if(val == dp[i+1][j]){
                sb.append(s2.charAt(j));
                j--;
        //Case 3 if the value is not the same compared with left or upper cell, append char and i--,j--
        //in longest common sequence, this means we find the common char
            } else {
                sb.append(s1.charAt(i));
                i--;j--;
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