package hatecode._0001_0999;

import java.util.HashSet;
import java.util.Set;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PalindromicSubstrings
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class PalindromicSubstrings {
    /**
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different 
substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.
     time : O(n^2);

     * @param s
     * @return
     */

    // space : O(n^2);
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - i <= 2) || dp[i + 1][j - 1]);
                if (dp[i][j]) res++;
            }
        }
        return res;
    }

    int count = 0;

    // space : O(n^2)/O(1)
    //thinking process: we want to know how many substirng are palindromic in s 
    
    //we start from any position in s, l move left while r move right
    //but needs to consider the even and odd case, it is like expand both ends
    int res = 1;
    public int countSubstrings_Recursive(String s) {
        if (s.length() == 0) return 0;
        
        for(int i = 0; i< s.length() - 1; i++) {
            helper(s, i, i); ////To check the palindrome of odd length palindromic sub-string
            helper(s, i, i+1); ////To check the palindrome of even length palindromic sub-string
        }
        return res;
    }
    
    public void helper(String s, int i, int j) {
        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){    //Check for the palindrome string 
            res++;    //Increment the count if palindromin substring found
            i--;    //To trace string in left direction
            j++;    //To trace string in right direction
        }
    }
    
    // own version of solutions
    public int countSubstrings3(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        
        Set<String> set = new HashSet<>();
        int len = s.length();
        for(int i = 0; i< len; i++) {
            for(int j = 0; j <= i; j++) {
                String first = s.substring(0, j), after = s.substring(j, i+1);
                if (isPanlid(first)) {
                    set.add("0->" + j);
                }
                if (isPanlid(after)) {
                    set.add(j + "->" + (i + 1));
                }
            }
        }
        return set.size();
    }
    
    public boolean isPanlid(String s) {
        if (s == null) {
            return false;
        }
        s= s.trim();
        if (s.length() < 1) {
            return false;
        }
        int left = 0, right = s.length()-1;
        while(left <=right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        
        return true;
    }
}
