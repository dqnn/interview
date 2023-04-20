package _2000_2999

import java.util.*;

public class _2055PlatesBetweenCandles {
    /*
    2055. Plates Between Candles
    There is a long table with a line of plates and candles arranged on top of it. You are given a 0-indexed string s consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.
    
    You are also given a 0-indexed 2D integer array queries where queries[i] = [lefti, righti] denotes the substring s[lefti...righti] (inclusive). For each query, you need to find the number of plates between candles that are in the substring. A plate is considered between candles if there is at least one candle to its left and at least one candle to its right in the substring.
    
    For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|". The number of plates between candles in this substring is 2, as each of the two plates has at least one candle in the substring to its left and right.
    Return an integer array answer where answer[i] is the answer to the ith query.
    
     
    
    Example 1:
    
    ex-1
    Input: s = "**|**|***|", queries = [[2,5],[5,9]]
    Output: [2,3]
    */
        
        public int[] platesBetweenCandles(String s, int[][] A) {
            if (s == null || s.length() < 1 || A == null || A.length < 1) return new int[]{};
            
            int n = s.length();
            int[] pc = new int[n], nc = new int[n];
            
            Arrays.fill(nc, Integer.MAX_VALUE);
            Arrays.fill(pc, Integer.MIN_VALUE);
            int[] sum = new int[n];
            for(int i =0; i<n; i++) {
                char c = s.charAt(i);
                pc[i] = c == '|'? i : i > 0 ? pc[i-1]:pc[i];
                nc[n-i-1] = s.charAt(n-i-1) == '|' ? n-i-1: i > 0? nc[n-i]: nc[n-i-1];
                if (c == '|') {
                    sum[i] = (i > 0 ? sum[i-1] : 0) + 1;
                } else sum[i] = i > 0 ? sum[i-1] : 0;
            }
            
            int[] res = new int[A.length];
            for(int i = 0; i<A.length; i++) {
                int start = A[i][0], end = A[i][1];
                int l = nc[start], r = pc[end];
                
                res[i] = l >= r ? 0 : r - l + 1 - (sum[r] - sum[l] + 1);
            }
            
            return res;
        }
        
        
        
        public int[] platesBetweenCandles_TreeSet(String s, int[][] A) {
            if (s == null || s.length() < 1 || A == null || A.length < 1) return new int[]{};
            
            TreeSet<Integer> tree = new TreeSet<>();
            for(int i = 0; i< s.length(); i++) {
                if(s.charAt(i) == '|') {
                    tree.add(i);
                }
            }
            
            
            int[] res = new int[A.length];
            for(int i = 0; i<A.length; i++) {
                int start = A[i][0], end = A[i][1];
                SortedSet<Integer> view = tree.subSet(start, true, end, true);
                if (view.size() <= 1){
                    res[i] = 0;
                    continue;
                }
                int sx = view.first(), ex = view.last();
                res[i] = ex - sx + 1 - view.size();
            }
            
            return res;
        }
    }