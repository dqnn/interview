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
    //thinking process:O(n)/O(1),1 is because we have limited character, max 26
    //given string s, return the smallest lexi order subsequence, which each character
    //in s will only appear once
    
    //
    public String smallestSubsequence(String s) {
        List<Character> res = new ArrayList<>();
        int[] used = new int[26];
        int[] cnt = new int[26];
        for(Character c : s.toCharArray()) cnt[c-'a']++;
        
        for(Character c : s.toCharArray()) {
            cnt[c-'a']--;
            if(used[c-'a']++ > 0) continue;
            while(res.size()>0 && res.get(res.size()-1) > c 
                    && cnt[res.get(res.size()-1)-'a'] > 0) {
                used[res.get(res.size()-1)-'a'] = 0;
                res.remove(res.size()-1);
            }
            res.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for(Character ch:res) sb.append(ch);
        return sb.toString();
    }
    
    //stack solution, O(n)/O(n), 
    public String smallestSubsequence_Stack(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;
        boolean[] visited = new boolean[26];
        
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            --cnt[c - 'a'];
            if (visited[c - 'a']) continue;
            while (!stack.isEmpty() && c < stack.peek() && cnt[stack.peek() - 'a'] > 0) {
                visited[stack.pop() - 'a'] = false;
            }
            stack.push(c);
            visited[c - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.insert(0, stack.pop());
        return sb.toString();
    }
}