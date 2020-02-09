package hatecode._0001_0999;

import java.util.*;
public class LetterCasePermutation {
/*
784. Letter Case Permutation
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
*/
    //ab->Ab and AB, aB, ab, so backtracking
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) return new ArrayList<>();
        
        Queue<String> q = new LinkedList<>();
        q.offer(s);
        //BFS
        for(int i =0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) continue;
            
            int size = q.size();
            for(int j =0; j< size; j++) {
                String cur = q.poll();
                char[] chs = cur.toCharArray();
                
                chs[i] = Character.toUpperCase(chs[i]);
                q.offer(new String(chs));
                chs[i] = Character.toLowerCase(chs[i]);
                q.offer(new String(chs));
            }
        }
        res.addAll(q);
        return res;
    }
    //this was pretty cool solution
    public List<String> letterCasePermutation_DFS(String S) {
        if (S == null) {
            return new LinkedList<>();
        }
        
        List<String> res = new LinkedList<>();
        helper(S.toCharArray(), res, 0);
        return res;
    }
    
    public void helper(char[] chs, List<String> res, int pos) {
        if (pos == chs.length) {
            res.add(new String(chs));
            return;
        }
        if (chs[pos] >= '0' && chs[pos] <= '9') {
            helper(chs, res, pos + 1);
            return;
        }
        
        chs[pos] = Character.toLowerCase(chs[pos]);
        helper(chs, res, pos + 1);
        
        chs[pos] = Character.toUpperCase(chs[pos]);
        helper(chs, res, pos + 1);
    }
}