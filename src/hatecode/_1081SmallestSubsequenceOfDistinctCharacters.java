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
    public String smallestSubsequence(String text) {
        List<Character> res = new ArrayList<>();
        int[] used = new int[26];
        int[] cnt = new int[26];
        for(Character ch : text.toCharArray()) cnt[ch-'a']++;
        for(Character ch : text.toCharArray()) 
        {
            cnt[ch-'a']--;
            if(used[ch-'a']++ > 0) continue;
            while(res.size()>0 && res.get(res.size()-1) > ch && cnt[res.get(res.size()-1)-'a'] > 0)
            {
                used[res.get(res.size()-1)-'a'] = 0;
                res.remove(res.size()-1);
            }
            res.add(ch);
        }
        StringBuilder sb = new StringBuilder();
        for(Character ch:res) sb.append(ch);
        return sb.toString();
    }
}