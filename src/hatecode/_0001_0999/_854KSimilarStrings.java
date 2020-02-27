package hatecode._0001_0999;

import java.util.*;
public class _854KSimilarStrings {
/*
854. K-Similar Strings
Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.

Given two anagrams A and B, return the smallest K for which A and B are K-similar.

Input: A = "aabc", B = "abca"
Output: 2
*/
    public int kSimilarity(String A, String B) {
        if (A == null && B == null) return 0;
        if (A.equals(B)) return 0;
        Queue<String> q = new LinkedList<>();
        q.offer(A);
        Set<String> visited = new HashSet<>();
        visited.add(A);
        int res = 0;
        while(!q.isEmpty()) {
            res++;
            int size = q.size();
            while (size-- > 0) {
                String str = q.poll();
            
                Set<String> combs = getCombs(str, B);
                //System.out.println(combs);
                if (combs.contains(B)) return res;
                for(String e : combs) {
                    if (visited.contains(e)) continue;
                    visited.add(e);
                    q.offer(e);
                }
            }
        }
        return -1;
    }
    
    private Set<String> getCombs(String s, String B) {
        int n = s.length();
        Set<String> res = new HashSet<>();
        int idx = 0;
        //we want to find which position can switch with idx from idx+1->n-1
        while(idx < n && s.charAt(idx) == B.charAt(idx)) idx++;
        for(int i = idx + 1; i< n; i++) {
            if (s.charAt(i)==B.charAt(i) || s.charAt(i) != B.charAt(idx)) continue;
                char[] chs = s.toCharArray();
                char temp = chs[i];
                chs[i] = chs[idx];
                chs[idx] = temp;
                res.add(new String(chs));  
        }
        return res;
    }
}