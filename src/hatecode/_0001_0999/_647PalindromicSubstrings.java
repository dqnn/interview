package hatecode._0001_0999;
public class _647PalindromicSubstrings {
/*
647. Palindromic Substrings
Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aasa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
*/
    
    //thinking process: O(n^2)/O(1)
    
    //the problem is to say, given one string, like "aaa", return the number
    //of palindrom strings.
    
    //palindrom string can be odd or even length, so we can count in the middle then
    //expand to both sides
    public int countSubstrings(String s) {
        if(s == null || s.length() < 1) return 0;
        
        int res = 0;
        for(int i =0;i<s.length(); i++) {
            res += helper(s, i, i);
            res += helper(s, i, i+1);
        }
        
        return res;
    }
    
    private int helper(String s, int i, int j) {
        int count = 0;
        while(i >=0 &&j < s.length() && s.charAt(i) == s.charAt(j)) {
            count++;
            i--;
            j++;
        }
        
        return count;
    }
}