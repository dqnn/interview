package hatecode._1000_1999;

import java.util.*;

public class _1128NumberOfEquivalentDominoPairs {
/*
1128. Number of Equivalent Domino Pairs
Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.

Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].

 

Example 1:

Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
Output: 1
*/
    //thinking process: O(n)/O(n) one pass, like two sum
    
    //the problem is to say: given a coordination diminoes, [i,j]
    //for two different point, we say they are the same if i = i1 && j = j1 ||
    //i = j1 && j = i1, so find out how many equal pairs.
    
    //so we want to design one key, which i can use to identify one point, 
    // best solution use the max * 10 + min, the bf use d[0]->d[1]
    //both of them are working, but we use two different way to calc
    
    //best solution use every time we use 1+ previous pair count, 
    //1+2+3+..n = n * (n + 1)/2
    //BF use the combination formula, choose 2 from n, then it is 
    //n(n-1)/2
    public int numEquivDominoPairs_Best(int[][] dominoes) {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] a : dominoes) {
            int max = Math.max(a[0], a[1]), min = Math.min(a[0], a[1]);
            int key = min * 10 + max;
            int pairs = map.getOrDefault(key, 0); // the number of dominoes already in the map is the number of the newly found pairs.
            cnt += pairs;
            map.put(key, 1 + pairs);
                               
        }
        return cnt;
    }
    
    //BF, O(n)/O(n)
    public int numEquivDominoPairs(int[][] ds) {
        if(ds == null || ds.length < 1 || ds[0].length < 1) return 0;
        
        Map<String, Integer> map = new HashMap<>();
        
        for(int[] d : ds) {
            String key = d[0] + "->" + d[1], key2 = d[1] +"->" + d[0];
            if(map.containsKey(key) || map.containsKey(key2)) {
                if(map.containsKey(key)) map.put(key, map.get(key)+1);
                else map.put(key2, map.get(key2)+1);
                continue;
            } else {
                map.put(key, 1);
            }
        }
        
        int res = 0;
        for(int v : map.values()) {
            res += v * (v - 1) / 2;
        }
        return res;
    }
}