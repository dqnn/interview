package hatecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PalindromePartitioning
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 131. Palindrome Partitioning
 */
public class PalindromePartitioning {
    /**
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s. max ones

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]

     For example, given s = "aab",
     Return

     [
     ["aa","b"],
     ["a","a","b"]
     ]

     time: O(n*2^n) space O(n)

     * @param s
     * @return
     */

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        helper(res, new ArrayList<>(), s);
        return res;
    }
    public void helper(List<List<String>> res, List<String> list, String s) {
        if (s.length() == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        // backtracking templates
        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s.substring(0, i + 1))) {
                list.add(s.substring(0, i + 1));
                helper(res, list, s.substring(i + 1));
                list.remove(list.size() - 1);
            }
        }
    }
    public boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
    
    //this is better solutions
    //we use a dp to record which substring is palidrome, so we can greatly reduce the time complexity
    //dp[j][i] means from  substring j->i, is palindrome or not. 
    //and then in backtracking loop, we only go deeper when we find one substring is palindrome. 
    //O(n * 2^n)/O(n^2)
    public List<List<String>> partition_DP_AND_BackGRACKING(String s) {
        List<List<String>> res = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j <= i; j++) {
                if(s.charAt(i) == s.charAt(j) && (i ==j || i == j +1 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                }
            }
        }
        helper(res, new ArrayList<>(), dp, s, 0);
        return res;
    }
    
    private void helper(List<List<String>> res, List<String> list, boolean[][] dp, String s, int pos) {
        if(pos == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        
        for(int i = pos; i < s.length(); i++) {
            if(dp[pos][i]) {
                list.add(s.substring(pos,i+1));
                helper(res, list, dp, s, i+1);
                list.remove(list.size()-1);
            }
        }
    }
    
    
}
