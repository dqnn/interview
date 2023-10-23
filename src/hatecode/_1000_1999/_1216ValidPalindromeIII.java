package hatecode._1000_1999;

import java.util.*;
public class _1216ValidPalindromeIII {
    /*
    1216. Valid Palindrome III
    Given a string s and an integer k, return true if s is a k-palindrome.

A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.

 

Example 1:

Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.
    */

    /*
     *    dp matrix
  [[1, 1, 1, 1, 1, 3, 5], 
   [0, 1, 1, 1, 1, 3, 3], 
   [0, 0, 1, 1, 1, 3, 3], 
   [0, 0, 0, 1, 1, 1, 1], 
   [0, 0, 0, 0, 1, 1, 1], 
   [0, 0, 0, 0, 0, 1, 1], 
   [0, 0, 0, 0, 0, 0, 1]]
     */
    
    //thinking process: O(n^2)/O(n^2)
    
    //the problem is to say: given one string and one integer k, 
    //return true if we can make s to be palindrome with removing up to k chars
    
    
    //we can translate the problem: 
    //if we can get the longest palindrme sequence of the string,
    //then if n - max len of palindrom of s <= k, then we can return true
    //this problem is the same as #516 Longest Palindromic Subsequence
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][n];

        //we have to iterater from n-1 to 0 because dp[i][j] depends on dp[i+1][j-1] etc
        //if we start from left, then it won't correct
        for(int i =n - 1; i>= 0; i--) {
            dp[i][i] = 1;
            
            for(int j = i + 1; j<n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j]= Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        
        return n - dp[0][n-1] <= k;
    
    }
    
    
    
    
    
    
    public boolean isValidPalindrome_TLE(String s, int k) {
        
        Set<String> visited = new HashSet<>();
        return helper(s, k, visited);
    }
    
    
    private boolean helper(String s, int k, Set<String> visited) {
        if (k < 0  || visited.contains(s)) {
            return false;
        }
        visited.add(s);
        
        if (isPalindrome(s)) return true;
        
        for(int i = 0; i<s.length(); i++) {
            String ns = s.substring(0, i) + s.substring(i+1);
            //System.out.println(ns);
            if(helper(ns, k - 1, visited)) return true;
        }
        return false;
    }
    
    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() -1;
        
        while(l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        
        return true;
    }
}