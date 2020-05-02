package hatecode._1000_1999;

import java.util.*;
public class _1163LastSubstringInLexicographicalOrder {
/*
1163. Last Substring in Lexicographical Order
Given a string s, return the last substring of s in lexicographical order.

 

Example 1:

Input: "abab"
Output: "bab"
Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically 
*/
    //thinking process: O(n)/O(1)
    
    //the problem is to say: given a string s, for all substrings of s, find the last substring with
    //max lexi order
    
    //what's the lexi order, like the order in dictionary
    //1. letter order
    //2. same prefix, then longer strings are in latter
    //so
    //1 we seek for largest char in the s.
    //2 all substring with this char should be considered. from the position to end
    //3 looping the 2 output list, remove first char, repeat 1 and 2 until we have 1 left
    
    //
    public String lastSubstring(String s) {
        int l = s.length();
        int i = 0, j = 1, k = 0;
        while(j + k < l) {
            if (s.charAt(i+k) == s.charAt(j+k)){
                k++;
                continue;
            }
            if (s.charAt(i+k) > s.charAt(j+k)){
                j++;
            } else {
                i = j;
                j = i + 1;
            }
            k = 0;
        }
        
        return s.substring(i);
    }
    
    public static String lastSubstring_More(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int stringLen = s.length();
        List<Integer> indexes = new ArrayList<>(stringLen);
        char[] chars = s.toCharArray();
        char max = 0;
        char c = 0, prevChar = 0;
        for (int i = 0; i < stringLen; i++) {
            prevChar = c;
            c = chars[i];
            if (c != prevChar && c > max) {
                max = c;
                indexes.clear();
                indexes.add(i);
            } else if (c != prevChar && c == max) {
                indexes.add(i);
            }
        }

        int one = 0;
        int two = 0;
        int maxIdxStart = 0;
        for (int i = 1; i < indexes.size(); i++) {
            one = indexes.get(maxIdxStart) + 1;
            two = indexes.get(i) + 1;
            max = 0;
            while (max == 0 && two < stringLen) {
                if (chars[one] > chars[two]) {
                    max = chars[one];
                } else if (chars[two] > chars[one]) {
                    max = chars[two];
                    maxIdxStart = i;
                }
                one++;
                two++;
            }
        }
        return s.substring(indexes.get(maxIdxStart));
    }
}