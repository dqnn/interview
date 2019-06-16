package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RegularExpressionMatching
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 10. Regular Expression Matching
 */
public class RegularExpressionMatching {
    /**
Given an input string (s) and a pattern (p), implement regular 
expression matching with support for '.' and '*'.

     '.' Matches any single character.
     '*' Matches zero or more of the preceding element.

     The matching should cover the entire input string (not partial).

     The function prototype should be:
     bool isMatch(const char *s, const char *p)

     Some examples:
     isMatch("aa","a") → false
     isMatch("aa","aa") → true
     isMatch("aaa","aa") → false
     isMatch("aa", "a*") → true
     isMatch("aa", ".*") → true
     isMatch("ab", ".*") → true
     isMatch("aab", "c*a*b") → true

     "aa", ".*"
     "ab", ".*"
     "aab", "c*a*b"


     boolean dp[i][j]的含义是s[0-i] 与 p[0-j]是否匹配。

     c* = empty

     1，p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1]
     2，If p.charAt(j) == ‘.’ : dp[i][j] = dp[i-1][j-1];
     3，If p.charAt(j) == ‘*’:
         here are two sub conditions:
         1，if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2] //in this case, a* only counts as empty
         2，if p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) == ‘.’:
             dp[i][j] = dp[i][j-1] // in this case, a* counts as single a, like dp[2][3]
             dp[i][j] = dp[i-1][j] //in this case, a* counts as multiple a, dp[3][4]
             dp[i][j] = dp[i][j-2] // in this case, a* counts as empty   dp[1][3]

xaabyc  xa*b.c
     x  a  *  b  .  c
  1  0  0  0  0  0  0
x 0  1  0  1  0  0  0
a 0  0  1  1  0  0  0
a 0  0  0  1  0  0  0
b 0  0  0  0  1  0  0
y 0  0  0  0  0  1  0
c 0  0  0  0  0  0  1

     time : O(m * n)
     space : O(m * n)

     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        //please note the len is s.length() + 1
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //initialize as true
        dp[0][0] = true;
        //why we initialize p first string first? because our calclation is row by row, so 
        //we initialize first row first
        for (int i = 0; i < p.length(); i++) {
            // we always use i+ 1 = i since our end is len + 1
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }
        //we loop two strings, row by row,  our len is 0-N, so our initialization 
        for(int i = 0; i< s.length(); i++){
            for(int j = 0; j < p.length(); j++) {
                //case 1 and 2
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                // common longest sequence
                if (p.charAt(j) == '*') {
                    // since * can be 0 or multiple times, which means we can remove this char,
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j -1]; 
                    } else {
                        dp[i+1][j+1] = dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1];
                    }
                    
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
