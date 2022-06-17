package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestPalindromicSubstring
 * Creator : professorX
 * Date : Sep, 2017
 * Description : TODO
 */
public class _005LongestPalindromicSubstring {
    /**
     * 5. Longest Palindromic Substring
     * Given a string s, find the longest palindromic substring in s.
     * You may assume that the maximum length of s is 1000.

     Example:

     Input: "babad"

     Output: "bab"

     Note: "aba" is also a valid answer.
     Example:

     Input: "cbbd"

     Output: "bb"


     * @param s
     * @return
     */

    // time : O(n^2) space : O(n^2);
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        //dp[l][r] means substring(l, r+1) is palindromic or not, substring is O(n), 
        //so here we use start and end to reduce the complexity
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start=0, end=0;
        for(int r = 0; r < s.length(); r++) {
            for(int l = 0; l <= r; l++) {
                // we need j - i because when s.charAt(i) == s.charAt(j), 
                // we want to know i+1 and j - 1 whether it is palindrome, so it has two cases
                // if there are only "aa", "a", "aba" these 3 cases, if more than that, 
                //we need to look for the value in 
                // dp[i+1][j-1], also dp[i+1[j-1] must be intialized in previous loop
                dp[l][r] = s.charAt(l) == s.charAt(r) && ((r - l <=2) || dp[l+1][r-1]);
                if (dp[l][r] && (r-l+1>(end - start + 1))) {
                    start = l;
                    end = r;
                }
            }
        }
        return n == 0 ? "" : s.substring(start, end + 1);
    }

    String res = "";
    // time : O(n^2) space : O(1)
    //we find one point and want to expand from that point(here, it is 0,1,2,,,n-1), either from both from i
    //or i, i+1
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return s;
        for (int i = 0; i < s.length(); i++) {
            //so it is odd length of string
            helper(s, i, i);
            // so it is even lenght of string
            helper(s, i, i + 1);
        }
        return res;
    }
    
    public void helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        String cur = s.substring(left + 1, right);
        if (cur.length() > res.length()) {
            res = cur;
        }
    }

    
    //Brute-force solution and TLE, just for reference
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        String res = "";
        for(int i = 0; i < s.length(); i++) {
            for(int j = i + 1; j <= s.length(); j++) {
                String temp = s.substring(i, j);
                if (isPalindrome(temp) && temp.length() > res.length()) {
                    res  = temp;
                }
            }
        }
        
        return res;
    }
    
    public boolean isPalindrome(String in) {
        if (in == null || in.length() < 1) {
            return true;
        }
        int i = 0, j = in.length() - 1;
        while(i <= j && in.charAt(i) == in.charAt(j)) {
            i ++;
            j --;
        }
        if (i <= j) {
            return false;
        } else {
            return true;
        }
        
    }
}
