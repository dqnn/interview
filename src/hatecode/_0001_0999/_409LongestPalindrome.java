package hatecode._0001_0999;

import java.util.HashSet;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestPalindrome
 * Creator : professorX
 * Date : Aug, 2018
 * Description : TODO
 */
public class _409LongestPalindrome {
    /**
     * 409. Longest Palindrome
     Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

     Explanation:
     One longest palindrome that can be built is "dccaccd", whose length is 7.

     time : O(n)
     * @param s
     * @return
     */
    // space : O(n)
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        HashSet<Character> set = new HashSet<>();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
                count++;
            } else {
                set.add(c);
            }
        }
        if (set.size() != 0) return count * 2 + 1;
        return count * 2;
    }
    // space : O(1)
    public int longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] count = new char[256];
        int res = 0;
        boolean bool = false;
        for (char c : s.toCharArray()) {
            if (count[c] > 0) {
                count[c]--;
                res++;
            } else {
                count[c]++;
            }
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) bool = true;
        }
        if (bool) return res * 2 + 1;
        return res * 2;
    }
    //O(n) O(1)
    public int longestPalindrome3(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        
        int[] chs = new int[256];
        for(int i = 0; i < s.length(); i++) {
            chs[s.charAt(i)]++;
        }
        int res = 0;
        for(int i = 0; i < 236; i++) {
            if (chs[i] > 0) {
                res += chs[i] % 2 == 0 ? chs[i] : chs[i] - 1;
            }
        }
        
        if (s.length() - res >= 1) {
            res++;
        }
        
        return res;
    }
}
