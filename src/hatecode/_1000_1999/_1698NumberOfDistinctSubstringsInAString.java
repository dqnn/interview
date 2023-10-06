package hatecode._1000_1999;

import java.util.*;

public class _1698NumberOfDistinctSubstringsInAString {
/*
1698. Number of Distinct Substrings in a String

Given a string s, return the number of distinct substrings of s.

A substring of a string is obtained by deleting any number of characters (possibly zero) from the front of the string and any number (possibly zero) from the back of the string.

 

Example 1:

Input: s = "aabbaba"
Output: 21
Explanation: The set of distinct strings is ["a","b","aa","bb","ab","ba","aab","abb","bab","bba","aba","aabb","abba","bbab","baba","aabba","abbab","bbaba","aabbab","abbaba","aabbaba"]

*/
    
/*
 * thinking process: O(nlgn)/O(n^2) n = s.length(), note we stored n^2 characters in suffix
 * 
 * the problem is to say: given one string s, you need to 
 * 
 * 
 */

     public int countDistinct(String s) {
        if (s == null || s.length() < 1) return 0;
         
        
        List<String> suffix = new ArrayList<>();
         
         for(int i = 0; i < s.length(); i++) {
             suffix.add(s.substring(i));
         }
         
         Collections.sort(suffix);
         
         int n = s.length();
         
         int res = n * (n+1)/2;
         for(int i =1; i< n; i++) {
             String prev = suffix.get(i-1);
             String cur = suffix.get(i);
             int len = Math.min(prev.length(), cur.length());
             for(int t = 0; t < len  && prev.charAt(t) == cur.charAt(t); t++) {
                res--;
             }
         }
         
         return res;
     }


     public int countDistinct_BF(String s) {
        if(s == null || s.length() < 1) return 0;
        
        Set<String> visited = new HashSet<>();
        
        int res = 0;
        for(int i = 0; i<s.length(); i++) {
            for(int j = i+1; j<=s.length(); j++) {
                String ss = s.substring(i, j);
                if(!visited.contains(ss)) {
                    res++;
                    visited.add(ss);
                }
            }
        }
        
        return res;
    }
    
}