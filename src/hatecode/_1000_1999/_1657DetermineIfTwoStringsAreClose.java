package hatecode._1000_1999;

import java.util.*;
public class _1657DetermineIfTwoStringsAreClose {
    /*
    1657. Determine if Two Strings Are Close
    
    Two strings are considered close if you can attain one from the other using the following operations:
    
    Operation 1: Swap any two existing characters.
    For example, abcde -> aecdb
    Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
    For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
    You can use the operations on either string as many times as necessary.
    
    Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
    
     
    
    Example 1:
    
    Input: word1 = "abc", word2 = "bca"
    Output: true
    */
    /*
     * thinking process: O(nlgn)/O(n)
     * 
     * the problem is to say: given 2 words w1 and w2. 
     */
        public boolean closeStrings(String w1, String w2) {
            if (w1.length() != w2.length()) return false;
            
            int[] a = new int[26];
            int[] b = new int[26];
            Set<Character> set = new HashSet<>();
            for(char c : w1.toCharArray()){
                a[c-'a']++;
                set.add(c);
            }
            
            for(char c : w2.toCharArray()){
                b[c-'a']++;
                set.remove(c);
            }
            
            boolean keyAndCountEquals = Arrays.toString(a).equals(Arrays.toString(b));
            Arrays.sort(a);
            Arrays.sort(b);
            boolean countEquals = Arrays.toString(a).equals(Arrays.toString(b));
            return (keyAndCountEquals||countEquals) && set.size() == 0;
        }
    }
