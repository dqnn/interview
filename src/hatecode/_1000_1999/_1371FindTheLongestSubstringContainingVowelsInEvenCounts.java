package hatecode._1000_1999;

import java.util.*;
public class _1371FindTheLongestSubstringContainingVowelsInEvenCounts
 {
/*
1371. Find the Longest Substring Containing Vowels in Even Counts
Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.

 

Example 1:

Input: s = "eleetminicoworoep"
Output: 13
*/
    //thinking process: O(n)/O(1)
    
    //
    public int findTheLongestSubstring_Best(String s) {
        Map<Character, Integer> map = new HashMap<>(){{
            put('a', 1);
            put('e', 2);
            put('i', 4);
            put('o', 8);
            put('u', 16);
        }};
        
        int state = 0, res = 0;
        Map<Integer, Integer> statesMap = new HashMap<>();
        statesMap.put(0, -1);
        for(int i = 0; i< s.length();i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int w = map.get(c);
                state ^= w;
            }
            statesMap.putIfAbsent(state, i);
            res = Math.max(res, i - statesMap.get(state));
        }
        return res;
        
    }
    
    //O(n^2)/O(1),
    
    //it is sliding window
     public int findTheLongestSubstring(String s) {
         Map<Character, Integer> map = new HashMap<>(){{
            put('a', 0);
            put('e', 0);
            put('i', 0);
            put('o', 0);
            put('u', 0);
             
         }};
         
         int res = 0, l = 0;
         for(int r = 0; r < s.length(); r++) {
             char c = s.charAt(r);
             if (map.containsKey(c)) {
                 map.put(c, 1+map.get(c));
             }
             
             Map<Character, Integer> temp = new HashMap<>(map);
             //System.out.println(temp);
             while(temp.get('a') % 2 == 1
                 ||temp.get('e') % 2 == 1
                 ||temp.get('i') % 2 == 1
                 ||temp.get('o') % 2 == 1
                 ||temp.get('u') % 2 == 1) {
                 char lf = s.charAt(l);
                 if(temp.containsKey(lf)) {
                     temp.put(lf, temp.get(lf) - 1);
                 }
                 l++;
             }
             
             res = Math.max(res, r - l + 1);
             l=0;
         }
         return res;
     }
    
    
    
    
}