package leetcode;

import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RemoveDuplicateLetters
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 316. Remove Duplicate Letters
 */
public class RemoveDuplicateLetters {
    /**
Given a string which contains only lowercase letters, remove duplicate letters 
so that every letter appear once and only once. You must make sure your result
 is the smallest in lexicographical order among all possible results.

Example 1:

Input: "bcabc"
Output: "abc"
Example 2:

Input: "cbacdcbc"
Output: "acdb"

     Example:
     Given "bcabc"
     Return "abc"

     Given "cbacdcbc"
     Return "acdb"

     c b a c d c b c
     0 1 2 3 4 5 6 7

     map :



     cba start = 3 end = 4 a
     cd start = 4 end = 4 ac
     d start = 5 end = 6 acd
     cb start = 7 end    acdb

     time : O(n)
     space : O(n)


     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return s;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        char[] res = new char[map.size()];
        int start = 0, end = findMinLastPos(map);
        for (int i = 0; i < res.length; i++) {
            char minChar = 'z' + 1;
            for (int k = start; k <= end; k++) {
                if (map.containsKey(s.charAt(k)) && s.charAt(k) < minChar) {
                    minChar = s.charAt(k);
                    start = k + 1;
                }
            }
            res[i] = minChar;
            map.remove(minChar);
            if (s.charAt(end) == minChar) {
                end = findMinLastPos(map);
            }
        }
        return new String(res);
    }

    public int findMinLastPos(HashMap<Character, Integer> map) {
        int res = Integer.MAX_VALUE;
        for (int num : map.values()) {
            res = Math.min(res, num);
        }
        return res;
    }
    
    /*
    Given the string s, the greedy choice (i.e., the leftmost letter in the answer) is the smallest s[i], s.t.
    the suffix s[i .. ] contains all the unique letters. (Note that, when there are more than one smallest s[i]'s, we choose the leftmost one. Why? Simply consider the example: "abcacb".)

    After determining the greedy choice s[i], we get a new string s' from s by

    removing all letters to the left of s[i],
    removing all s[i]'s from s.
    We then recursively solve the problem w.r.t. s'.
        */
        public String removeDuplicateLetters2(String s) {
            if (s == null || s.length() < 1) {
                return "";
            }
            int[] cnt = new int[26];
            int pos = 0; // the position for the smallest s[i]
            for (int i = 0; i < s.length(); i++) {
                //lexeco sort
                cnt[s.charAt(i) - 'a']++;
            }
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) < s.charAt(pos)) {
                    pos = i;
                }
                if (--cnt[s.charAt(i) - 'a'] == 0) {
                    break;
                }
            }
            return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
        }
}
