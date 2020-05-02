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