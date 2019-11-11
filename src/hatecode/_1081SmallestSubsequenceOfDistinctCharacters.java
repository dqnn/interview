package hatecode;

import java.util.*;
public class _1081SmallestSubsequenceOfDistinctCharacters {
/*
1081. Smallest Subsequence of Distinct Characters
Return the lexicographically smallest subsequence of text that contains all the distinct characters of text exactly once.

Example 1:

Input: "cdadabcc"
Output: "adbc"
*/
    //thinking process:
    //given string s, return the smallest lexi order subsequence, which each character
    //in s will only appear once
    
    //
    public String smallestSubsequence(String s) {
        List<Character> res = new ArrayList<>();
        int[] used = new int[26];
        int[] cnt = new int[26];
        for(Character c : s.toCharArray()) cnt[c-'a']++;
        for(Character c : s.toCharArray()) 
        {
            cnt[c-'a']--;
            if(used[c-'a']++ > 0) continue;
            while(res.size()>0 && res.get(res.size()-1) > c && cnt[res.get(res.size()-1)-'a'] > 0)
            {
                used[res.get(res.size()-1)-'a'] = 0;
                res.remove(res.size()-1);
            }
            res.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for(Character ch:res) sb.append(ch);
        return sb.toString();
    }
}