package hatecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : IsSubsequence
 * Creator : duqiang
 * Date : July, 2018
 * Description : TODO
 */
public class IsSubsequence {
    /**
     * 392. Is Subsequence
     * 
     * Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up: this is one more step:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
     
     * 
     * Example 1:
     s = "abc", t = "ahbgdc"

     Return true.

     Example 2:
     s = "axc", t = "ahbgdc"

     Return false.

     time : O(n);
     space : O(1);
     * @param s
     * @param t
     * @return
     */
    // so for the follow up, how can we prepare: if t is pretty huge string
    //to improve the whole problem efficiency, need to do work on t
    // need to ask interviewer that whether we can process S strings first, because 
    // 
    //O(max(m,n))/O(1)
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) return true;
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) i++;
            j++;
        }
        return i == s.length();
    }
    
 // Follow-up: O(N) time for pre-processing, O(Mlog?) for each S.
    // Eg-1. s="abc", t="bahbgdca"
    // idx=[a={1,7}, b={0,3}, c={6}]
    //  i=0 ('a'): prev=1
    //  i=1 ('b'): prev=3
    //  i=2 ('c'): prev=6 (return true)
    // Eg-2. s="abc", t="bahgdcb"
    // idx=[a={1}, b={0,6}, c={5}]
    //  i=0 ('a'): prev=1
    //  i=1 ('b'): prev=6
    //  i=2 ('c'): prev=? (return false)
    
    // so how about setup BST or TreeMap instead of array of list
    public boolean isSubsequence2(String s, String t) {
        // so we set up a list
        // a--1,3,5,6,7
        // b--0,4,8,9,
        // a is character in t, the list contains the index in t
        List<Integer>[] idx = new List[256]; // Just for clarity, List<Integer>[] idx = new ArrayList[256]; also work
        for (int i = 0; i < t.length(); i++) {
            if (idx[t.charAt(i)] == null)
                idx[t.charAt(i)] = new ArrayList<>();
            idx[t.charAt(i)].add(i);
        }
        
        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            if (idx[s.charAt(i)] == null) return false; // Note: char of S does NOT exist in T causing NPE
           //If key is not present, the it returns "(-(insertion point) - 1)". 
            //The insertion point is defined as the point at which the key 
            // would be inserted into the list.
            //// Returns index of key in sorted list sorted in
            // ascending order
            int j = Collections.binarySearch(idx[s.charAt(i)], prev); // preV is the key
            // means we did not find it
            if (j < 0) j = -j - 1; 
            // means there is no such character in t
            if (j == idx[s.charAt(i)].size()) return false;
            // j existed, so pre is the index of t, so pre move 1 character
            prev = idx[s.charAt(i)].get(j) + 1;
        }
        return true;
    }
    //simple DP solutions,
    //so s = "abc", t = "ahbgdc", dp[i][j] means s(0,i) is sequence of t(0,j)
    //s[i] = t[j] then dp[i][j] = dp[i-1][j-1] 
    //if not, then dp[i][j] = d[i][j-1] because comapre "ab"(s) to "a" (t), we can see only
    //only left is true can guarantee that it is sequence, the right top cannot
    public boolean isSubsequence_DP(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        
        int m = s.length(), n = t.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        for(int i = 0; i<= n; i++) dp[0][i] = true;
        
        for(int i = 1; i<=m; i++) {
            for(int j = 1; j<=n; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = dp[i][j-1];
            }
        }
        return dp[m][n];
        
    }
}
