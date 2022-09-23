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
     */
    public int shortestWay_best(String src, String dst) {
        char[] cs = src.toCharArray(), ts = dst.toCharArray();
        
        int n = src.length();
        int[][] map = new int[n][26];
        map[n - 1][cs[n - 1] - 'a'] = cs.length; 
        for (int i = n - 2; i >= 0; i--) {
            map[i] = Arrays.copyOf(map[i + 1],26);
            map[i][cs[i] - 'a'] = i + 1; 
        }
        
        int j = 0, res = 1;
        for (int i = 0; i < ts.length; i++) {
            if (j == n) {
                j = 0;
                res++;
            }
            j = map[j][ts[i] - 'a'];
            if (map[0][ts[i] - 'a'] == 0) return -1;
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