package hatecode._0001_0999;

import java.util.*;
import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestSubstringwithAtLeastKRepeatingCharacters
 * Creator : professorX
 * Date : Aug, 2018
 * Description : 395. Longest Substring with At Least K Repeating Characters
 */
public class _395LongestSubstringwithAtLeastKRepeatingCharacters {
    /**
     * Find the length of the longest substring T of a given string (consists of lowercase letters only)
     * such that every character in T appears no less than k times.

     Example 1:

     Input:
     s = "aaabb", k = 3

     Output:
     3

     The longest substring is "aaa", as 'a' is repeated 3 times.
     Example 2:

     Input:
     s = "ababbc", k = 2

     Output:
     5

     The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.


     * @param s
     * @param k
     * @return
     */

    // time : O(n) space : O(1)
    /* interview friendly, thinking process: 
     * For h=1:26, we are going to use sliding window (left i, right j) to 
     * find the "longest window which contains exactly h unique characters 
     * and for each character, there are at least K repeating ones".
For example, when h=3, K=5, we are going to find the longest window contains exactly 3 unique 
characters and each repeating 5 times.
     */
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        for(int i =1; i<=26; i++) {
            res = Math.max(res, helper(s, k, i));
        }
        return res;
    }
    
    private int helper(String s, int k, int target) {
        Map<Character, Integer> map = new HashMap<>();
        int l =0, r = 0;
        int nolessThank = 0;
        int res = Integer.MIN_VALUE;
        while(r < s.length()) {
            char rc = s.charAt(r);
            map.put(rc, map.getOrDefault(rc, 0) + 1);
            //means at least we have one character repeated k times, so we count character repeating time++
            if(map.get(rc) == k) nolessThank++;
            r++;
            
            while(map.size() > target) {
                char lc = s.charAt(l);
                //we are going to move l to l++,so lc repeating k time character count in this window will decrease by 1
                int cnt = map.get(lc);
                if(cnt == k) nolessThank--;
                //remove this char from TP window
                if(cnt == 1) map.remove(lc);
                else map.put(lc, cnt - 1);
                
                l++;
            }
            if(map.size() == target && map.size() == nolessThank) {
                res = Math.max(res, r-l);
            }
        }
        return res;
    }
    
    
    // not understand this solution
    public int longestSubstring2(String s, int k) {
        char[] str = s.toCharArray();
        return helper(str,0,s.length(),k);
    }
    private int helper(char[] str, int start, int end,  int k){
        if (end - start < k) return 0;//substring length shorter than k.
        int[] count = new int [26];
        for (int i = start; i<end; i++) {
            int idx = str[i] - 'a';
            count[idx]++;
        }
        for (int i=0; i<26; i++) {
            if (count[i] < k && count[i] > 0) { //count[i]=0 => i+'a' does not exist in the string, skip it.
                for (int j = start; j<end; j++) {
                    if (str[j] == i+'a') {
                        //divide into two parts
                        int left = helper(str, start, j, k);
                        int right = helper(str, j+1, end, k);
                        return Math.max(left, right);
                    }
                }
            }
        }
        return end - start;
    }
    
    //recursive one, so 
    public int longestSubstring3(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = new char[26];
        // record the frequency of each character
        for (int i = 0; i < s.length(); i += 1) chars[s.charAt(i) - 'a'] += 1;
        boolean flag = true;
        for (int i = 0; i < chars.length; i += 1) {
            if (chars[i] < k && chars[i] > 0) flag = false;
        }
        // return the length of string if this string is a valid string
        if (flag == true) return s.length();
        int result = 0;
        int start = 0, cur = 0;
        // otherwise we use all the infrequent elements as splits
        while (cur < s.length()) {
            if (chars[s.charAt(cur) - 'a'] < k) {
                //
                result = Math.max(result, longestSubstring3(s.substring(start, cur), k));
                start = cur + 1;
            }
            cur++;
        }
        result = Math.max(result, longestSubstring3(s.substring(start), k));
        return result;
    }
}
