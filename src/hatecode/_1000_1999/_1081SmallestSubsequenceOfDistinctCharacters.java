package hatecode._1000_1999;

import java.util.*;
public class _1081SmallestSubsequenceOfDistinctCharacters {
/*
1081. Smallest Subsequence of Distinct Characters
Return the lexicographically smallest subsequence of text that contains all the distinct characters of text exactly once.

Example 1:

Input: "cdadabcc"
Output: "adbc"
*/
    //thinking process:O(n)/O(1),1 is because we have limited character, max 26,
    //O(n) if we think res used n space
    //given string s, return the smallest lexi order subsequence, which each character
    //in s will only appear once
    
    //for example, cdadabcc
    //c,d will be in res, then come a, we found d>a and cnt['d'-'a']> 0, so we remove d,
    //we compare c vs a, still remove c, and put a there, res is used like a stack. see 
    //below stack solution
    
    //so we use used[] to indicate whether we used this character or not, everytime, we would
    //increase 1,
    //we get char c, and comapre to last character in res, if it is bigger than current char
    //and last character count bigger than 0, then we can remove last character and mark
    //used[] as 0, this step is to remove non-qualified sequence
    
    public String smallestSubsequence(String s) {
        List<Character> res = new ArrayList<>();
        int[] used = new int[26];
        int[] cnt = new int[26];
        for(char c : s.toCharArray()) cnt[c-'a']++;
        
        for(char c : s.toCharArray()) {
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