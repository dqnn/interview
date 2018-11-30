package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FindAllAnagramsinaString
 * Creator : duqiang
 * Date : July, 2018
 * Description : 438. Find All Anagrams in a String
 */
public class FindAllAnagramsinaString {
    /**
     * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

     Strings consists of lowercase English letters only and the length of
     both strings s and p will not be larger than 20,100.

     The order of output does not matter.

     Example 1:

     Input:
     s: "cbaebabacd" p: "abc"

     Output:
     [0, 6]

     Explanation:
     The substring with start index = 0 is "cba", which is an anagram of "abc".
     The substring with start index = 6 is "bac", which is an anagram of "abc".

     0 1 2 3 4 5 6 7 8 9
     c b a e b a b a c d
                   e
             s

     time : O(n)
     space : O(n)


     * @param s
     * @param p
     * @return
     */
        //thinking process: two pointers
        // we change change to templates which use two while and get the answer, but here is more elegant version
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new ArrayList<>();
            //edge case
            if (s == null || p == null || s.length() < 1 || p.length() < 1 || s.length() < p.length()) {
                return res;
            }
            
            int[] visited = new int[26];
            for(char ch : p.toCharArray()) {
                visited[ch - 'a'] ++;
            }
            int start = 0, end = 0;
            int count = p.length();
            while(end < s.length()) {
                // end here means idx = p.length() + 1 position,  
                // s: "cbaebabacd" p: "abc",
                // 0-3, end -> e but we need to move the window to right, 
                // so start->a, this element must be removed from the window, the count here means how many elements are not the same,
                // so count has been count++, because we find this element in p and will be removed from the window.  
                
                if (end - start == p.length()) {
                    if (visited[s.charAt(start) - 'a'] >= 0) {
                        count++;
                    }
                    //we need remove the side effect from removing start-> a in the moving window. 
                    //visited array need to ++1 no matter p contains it or not because we have done to start->a, so visited needs to be reset for this index
                    visited[s.charAt(start) - 'a']++;
                    //last start needs ++, becasue  start move to next element
                    start++;   
                }
                // count -- because we find the element in P, and so diff has to decrease 1
                if (visited[s.charAt(end++) - 'a']-- >= 1) {//if (--visited[s.charAt(end++) - 'a']-- >= 0) also works
                    count--;
                }
                if (count == 0) {
                    res.add(start);
                }
            }
        
            return res;
        }
    
    //another TLE solution
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || p == null || s.length() < 1 || p.length() < 1 || p.length() > s.length()) {
            return res;
        }
        int idx = 0;
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            idx = s.indexOf(ch, idx);
            
            while(idx > -1) {
                if (isSame(s, p, idx)) {
                    set.add(idx);
                }
                idx = s.indexOf(ch, idx + 1);
            }
        }
        res.addAll(set);
        return res;
    }
    
    private boolean isSame(String s, String t, int start) {
        if (start + t.length() > s.length()) {
            return false;
        }
 
        List<String> list = new ArrayList<>();
        for(int i =0; i < t.length(); i++) {
            list.add(String.valueOf(t.charAt(i)));
        }
        
        for(int i = start; i < start + t.length(); i++) {
            String x = s.substring(i, i+1);
            if (list.indexOf(x) == -1) {
                return false;
            } else {
                list.remove(x);
            }
        }
        return true;
    }
 }
