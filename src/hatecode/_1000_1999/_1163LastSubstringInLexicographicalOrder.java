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
    
    /*
1."i" is the starting index of the first substring
2."j" is the staring index of the second substring
3."k" is related to substring.length() (eg. "k" is substring.length()-1)

Case 1 (s[i+k]==s[j+k]):
-> If s.substr(i,k+1)==s.substr(j,k+1), we keep increasing k.
Case 2 (s[i+k]<s[j+k]):
-> If the second substring is larger, we update i with j. (The final answer is s.substr(i))
Case 3 (s[i+k]>s[j+k]):
-> If the second substring is smaller, we just change the starting index of 
the second string to j+k+1. Because s[j]~s[j+k] must be less than s[i], otherwise "i" 
will be updated by "j". So the next possible candidate is "j+k+1".
     */
    
    //need more time to understand this
    public String lastSubstring(String s) {
        int l = s.length();
        
        //i is first string starting index
        //j is 2nd string starting index
        //w is the width of both strings
        int i = 0, j = 1, w = 0;
        while(j + w < l) {
            if (s.charAt(i+w) == s.charAt(j+w)){
                w++;
                continue;
            }
            if (s.charAt(i+w) > s.charAt(j+w)){
                j++;
            } else {
                i = j;
                j = i + 1;
            }
            w = 0;
        }
        
        return s.substring(i);
    }
    
    //encoding solution
    /*Traverse the input string reversely, encode all suffices and compare them and update max and the corresponding index. e.g., for "bba",
    radix is 2, and we have suffices "a", "ba", "bba" and their encoding are as follows:
        0 * 2 * 2 = 0, 0 * 2 + 1 * 2 * 2 = 4, 0 + 1 * 2 + 1 * 2 * 2 = 6

        We can use TreeSet.headSet(key).size() to get indices of corresponding characters.
        */
    public String lastSubstring_encoding(String s) {
        TreeSet<Character> ts = new TreeSet<>();
        for (char c: s.toCharArray()) ts.add(c);
        
        //distinct char count
        int distinctCharCnt = ts.size(), lo = 0;
        double max = 0d, cur = 0d;
        //we traverse back
        for (int i = s.length() - 1; i >= 0; --i) {
            //headset means less than 
            cur = ts.headSet(s.charAt(i)).size() + cur / distinctCharCnt;
            if (max <= cur) {
                max = cur;
                lo = i;
            }
        }
        return s.substring(lo);
    }
}