package hatecode;

import java.util.*;

public class _1100FindKLengthSubstringsWithNoRepeatedCharacters {
/*
1100. Find K-Length Substrings With No Repeated Characters
Given a string S, return the number of substrings of length K with no repeated characters.

 

Example 1:

Input: S = "havefunonleetcode", K = 5
Output: 6
*/
    //thinking process: O(n)/O(n)
    
    //given string s and integer k, return how many substring which length = k and no
    //dup char
    
    //sliding window, here we just use set which is more convenient
    public int numKLenSubstrNoRepeats(String S, int k) {
        if (S == null || S.length() < 1 || k <= 0) return 0;
        
        Set<Character> set = new HashSet<>();
        int res = 0, i = 0;
        for (int j = 0; j < S.length(); j++) {
            while (set.contains(S.charAt(j)))
                set.remove(S.charAt(i++));
            set.add(S.charAt(j));
            res += j - i + 1 >= k ? 1 : 0;
        }
        return res;   
    }
}