package hatecode._1000_1999;

import java.util.*;
public class _1055ShortestWayToFormString {
    /*
     * 1055. Shortest Way to Form String From any string, we can form a subsequence
     * of that string by deleting some number of characters (possibly no deletions).
     * 
     * Given two strings source and target, return the minimum number of
     * subsequences of source such that their concatenation equals target. If the
     * task is impossible, return -1.
     * Example 1:
     * 
     * Input: source = "abc", target = "abcbc" Output: 2
     */
    //binary search O(s + t + tlgs)/O(s)
    public int shortestWay_BS(String s, String t) {
        if (s == null && t== null || t ==null) return 0;
        if (s == null) return -1;
        
        Map<Character, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(s.charAt(i), v->new ArrayList<>()).add(i);
        }
        
        for(char ch: t.toCharArray()) {
            if (!map.containsKey(ch)) return -1;
        }
        
        
        int res = 1;
        int prev = -1;
        for (int i = 0; i < t.length(); i++) {
            List<Integer> list = map.get(t.charAt(i));
            int next = binarySearch(list, prev);
            if (next <= prev) {
                res++;
                prev = list.get(0);
            } else {
                prev = next;
            }
        }
        return res;
    }
    
    // next el largest than target
    private int binarySearch(List<Integer> list, int target) {
        int at = Collections.binarySearch(list, target);
        at = (at < 0) ? ~at : at + 1;
        return at >= list.size() ? -1 : list.get(at);
    }
    
    /*
     * interview friendly: O(m+n)
     * 
     * two pointer
     * 
     * 
     */
    public int shortestWay_TP(String s, String t) {
        if(s == null || s.length() < 1 || t== null || t.length() < 1) return -1;
        
        Integer[] cnt = new Integer[26];
        for(int i = 0; i< s.length(); i++) cnt[s.charAt(i)-'a'] = i;
        
        int res = 1, j = 0;
        for(int i = 0; i<t.length(); i++, j++) {
            char c = t.charAt(i);
            if(cnt[c-'a'] == null) return -1;
            while(j < s.length() && s.charAt(j) != c) {
                j++;
            }
            
            if (j == s.length()) {
                res++;
                j =-1;
                i--;
            }
        }
        
        return res;
    }

    /*
     * interview friendly O (m + n)
     * 
     * map[i][c-'a'] means for string s, position i, 
     * 
     */
    public int shortestWay_best(String s, String t) {
        char[] cs = s.toCharArray(), ct = t.toCharArray();
        
        int n = s.length();
        int[][] map = new int[n][26];
        map[n - 1][cs[n - 1] - 'a'] = n; 
        for (int i = n - 2; i >= 0; i--) {
            map[i] = Arrays.copyOf(map[i + 1],26);
            map[i][cs[i] - 'a'] = i + 1; 
        }
        
        int j = 0, res = 1;
        for (int i = 0; i < ct.length; i++) {
            if (j == n) {
                j = 0;
                res++;
            }
            j = map[j][ct[i] - 'a'];
            if (map[0][ct[i] - 'a'] == 0) return -1;
            if (j == 0) {
                res++;
                i--;
            }
        }
        return res;
    }
    
    
    
    //s=abc, t=abcbc
    //greedy thinking, so we have lastIndex in s, each time, we would compare
    //t.charAt(i) from lastIndex in s, if we can find the the char in s(index), then we move
    //lastIndex to index + 1, 
    //if we cannot find the index, then we reset lastIndex = 0, search from starting, and this means 
    //we have to arrange the sub sequence one more time
    public int shortestWay(String s, String t) {
        int res = 0;
        int lastIndex = 0;
        for(int i = 0; i < t.length(); i++){
            int index = s.indexOf(t.charAt(i), lastIndex);
            if(index < 0){
                if(lastIndex == 0) return -1;
                lastIndex = 0;
                res++;
                i--;
            } else {
                lastIndex = index + 1;
            }
        }
        return res+1;
    }
    
    
}