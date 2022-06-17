package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestSubstringwithAtMostKDistinctCharacters
 * Creator : professorX
 * Date : Aug, 2018
 * Description : 340. Longest Substring with At Most K Distinct Characters
 */
public class _340LongestSubstringwithAtMostKDistinctCharacters {
    /**
     * Given a string, find the length of the longest substring T that contains
     * at most k distinct characters.

     For example, Given s = “eceba” and k = 2,

     T is "ece" which its length is 3.

     sliding window

     time : O(n)
     space : O(1)

     * @param s
     * @param k
     * @return
     */
    //two pointers to find the longest
    // we scan the string s from 0 to s.length() - 1, 
    // we will increment num by having unique char until bigger than k, 
    // if it bigger, then we need another pointer j from beginning to scan to reduce num
    // and we try tested again. 
    // this will be biggest
    //note, when we count the chat, we ++ at end of count, but when we -- when left point moves
    
    //some key points:
    
    //1 note we should always move r to right, see TP, we should always move to right
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int res = 0, num = 0, j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i)]++ == 0) num++;
            if (num > k) {
                while (--count[s.charAt(j++)] > 0);
                num--;
            }
            /* this also works to replace 41- 44
             *while (num > k) {
                if (--count[s.charAt(i++)]== 0) {
                    num--;
                }
             }
             */
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
    //same as above, but just use TWO pointers
    public int lengthOfLongestSubstringKDistinct_TP(String s, int k) {
        if (s == null || s.length() < 1 || k < 1) return 0;
        int[] cnt = new int[256];
        int l = 0, r = l, count = 0, res = 0;
        while(r < s.length()) {
            if (cnt[s.charAt(r)] ++ == 0) count ++;
            while (count > k) {
                if (--cnt[s.charAt(l++)]== 0) {
                    count--;
                }
             }
            res = Math.max(res, r - l + 1);
            r++;
        }
        return res;
    }
    
    public int lengthOfLongestSubstringKDistinct_Map(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int l = 0;
        int res = Integer.MIN_VALUE;
        for(int r = 0; r<s.length(); r++) {
            char d = s.charAt(r);
            map.put(d, map.getOrDefault(d, 0) + 1);
            
            while(map.size() > k) {
                char c = s.charAt(l);
                if (map.get(c) > 1) map.put(c, map.get(c) - 1);
                else map.remove(c);
                l++;
            }
            
            res = Math.max(res, r - l + 1);
        }
        
        return res;
    }
}
