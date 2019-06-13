package hatecode;

import java.util.*;
/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MinimumWindowSubstring
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 76. Minimum Window Substring
 */
public class MinimumWindowSubstring {
    /**
     * sliding window
     * Given a string S and a string T, find the minimum window in S which will contain all the characters
     * in T in complexity O(n).

     For example,
     S = "ADOBECODEBANC"
     T = "ABC"
     Minimum window is "BANC".

     Note:
     If there is no such window in S that covers all characters in T, return the empty string "".

     If there are multiple such windows, you are guaranteed that there will always be only one unique
     minimum window in S.

     test case:
     S = "ADOBECODEBANC"
     T = "ABC"

     ADOBEC
     DOBECODEBA
     OBECODEBA
     BECODEBA
     ECODEBA
     CODEBA
     ODEBANC
     DEBANC
     EBANC
     BANC

            A B C D E F G H    O
     count: 0 0 0 -1-1         -1
            0 1 2 3 4 5 6 7 ...

     A D O B E C O D E B A N C
               i
     CODEBA
     total = 1

     time : O(n)
     space : O(1)

     * @param s
     * @param t
     * @return
     */
    //thinking process: this is the same as 438. Find All Anagrams in a String 
    //best templates, all TP should use this
    public static String minWindow(String s, String t) {
        if(s == null && t == null) return "";
        if(s == null || t == null) return "";
        
        Map<Character, Integer> map = new HashMap<>();
        for(char c: t.toCharArray()) map.put(c, map.getOrDefault(c,0) + 1);
        //unique distinct character
        int count = map.size();
        int l =0, r =0;
        int len = Integer.MAX_VALUE;
        String res = "";
        while(r < s.length()) {
            char c = s.charAt(r);
            if(map.containsKey(c)) {
                map.put(c, map.getOrDefault(c, 0) - 1);
                if(map.get(c) == 0) count--;
            }
            r++;
            //only move left when condition satisfied
            while(count == 0) {
                char lc = s.charAt(l);
                if(map.containsKey(lc)) {
                    map.put(lc, map.getOrDefault(lc, 0) + 1);
                    if(map.get(lc) > 0) count++;
                }
                
                if(r -l < len) {
                    len = r-l;
                    res = s.substring(l, r);
                }
                l++;
            }
        }
        return res;
    }
    
    //this is template code for all substring ,sub array, and related sliding window questions
    public String minWindow_Template(String s, String t) {
        int[] map = new int[128];

        for (char c : t.toCharArray())
            map[c]++;

        int count = t.length(), begin = 0, end = 0, d = Integer.MAX_VALUE, head = 0;

        while (end < s.length()) {
            if (map[s.charAt(end)] > 0){
                count--;
            }
            map[s.charAt(end)]--;
            end++;
            while (count == 0) {
                if (end - begin < d) {
                    d = end - begin;
                    head = begin;
                }
                if (map[s.charAt(begin)] == 0)count++;
                map[s.charAt(begin)]++;
                begin++;
            }
        }

        return d == Integer.MAX_VALUE ? "" : s.substring(head, head + d);

    }
    
    public static void main(String[] args) {
        String S = "AOBECOEBANC", T = "ABC";
        System.out.println(minWindow(S, T));
    }
}
