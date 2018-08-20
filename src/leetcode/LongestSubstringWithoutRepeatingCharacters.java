package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestSubstringWithoutRepeatingCharacters
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : TODO
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * 3. Longest Substring Without Repeating Characters
     * Examples:

     Given "abcabcbb", the answer is "abc", which the length is 3.

     Given "bbbbb", the answer is "b", with the length of 1.

     Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring,
     "pwke" is a subsequence and not a substring.


     time : O(n)
     space : O(n)
     * @param s
     * @return
     */
    // we use a map to store char-> idx, so we use two pointers i and j.
    // when we scan from left to right, 
    // if find the char in the map, then we know the left pointer position max(j, map.get(ch) + 1
    // and we update new char since left pointer move one more step， the length of the string is i -j +1
    // the key of the problem is to understand how we move the left and right pointers
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
   
    // we use array as map to store the char in the string, count[ch] = idx,
    // so basical principal is the same as previous one.
    //very important is we compare to start using equals and count initialized as -1 not 0.
    //because array starts from 0 and 
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        
        int[] count = new int[256];
        Arrays.fill(count, -1);
        int start = 0, res = 0;
        for(int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i)] >= start) {
                start = count[s.charAt(i)] + 1 ;
            }
            
            count[s.charAt(i)] = i ;
            res = Math.max(res, i - start + 1);
        }
        return res;
    }
}
