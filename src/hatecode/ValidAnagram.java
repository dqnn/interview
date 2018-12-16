package hatecode;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ValidAnagram
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 242. Valid Anagram
 */
public class ValidAnagram {
    /**
Given two strings s and t , write a function to determine if t is an 
anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt 
your solution to such case?

     * @param s
     * @param t
     * @return
     */

    //time : O(nlogn) space : O(n)
    //thinking process:
    //the problem is to say, change the order of chars in s could be t. 
    
    //so we can sort and do string compare
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    //time : O(n) space : O(1)
    //another we can do char count, we can compare each char count is the same or we 
    //just reduce them to 0, and validate each one
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int num : count) {
            if (num != 0) return false;
        }
        return true;
    }
}
