package hatecode._1000_1999;

import java.util.*;
public class _1202SmallestStringWithSwaps {
/*
1202. Smallest String With Swaps
You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.

 

Example 1:

Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"
*/
    
    class DSU {
        private int[] parent;
        public DSU(int n) {
            parent = new int[n];
            for(int i = 0; i<n; i++) parent[i] = i;
        }
        
        public int find(int x) {
            while (x != parent[x]) {
                x = parent[x];
            }
            return x;
        }
        public void union(int x, int y) {
            int i = find(x), j = find(y);

            if (i < j) parent[j] = i;
            else parent[i] = j;
        }
    }
    
    //thinking process: O(nlgn)/O(n)
    //the problem is to say: given one string and one 2D array paris[],
    //pairs[0] = [1, 2] means s[1]<->s[2] can be switched, and no times limitation.
    //return the smallest lex order string.
    
    //so we can easily see that for the positions in this string, we have multiple choices,
    //and position 0 has highest priority, each position can be changed to any character, if we sorted 
    //all candidate for each position and then we just picked up the smallest one. 
    
    //we use Union find to find qualified candidates which are can be placed in that position, the parent node is the 
    //position index, then we sort the candidates, pop the smallest one, then continue to next position,
    //if next position aso belong to previous component that's fine, because previous character already poped out, so we 
    //can easily figured out that we need PriorityQueue
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s == null || s.length() < 1) {
            return null;
        }
        
        DSU dsu = new DSU(s.length());
        
        for (List<Integer> pair : pairs) {
            dsu.union(pair.get(0), pair.get(1));
        }
        
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(dsu.find(i), v->new PriorityQueue<>()).add(s.charAt(i));
        }
        //System.out.println(map);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(map.get(dsu.find(i)).poll());
        }
        return sb.toString();
    }
}