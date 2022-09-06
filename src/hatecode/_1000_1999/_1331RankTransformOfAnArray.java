package hatecode._1000_1999;

import java.util.*;
public class _1331RankTransformOfAnArray {
/*
1331. Rank Transform of an Array
Given an array of integers arr, replace each element with its rank.

The rank represents how large the element is. The rank has the following rules:

Rank is an integer starting from 1.
The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
Rank should be as small as possible.
 

Example 1:

Input: arr = [40,10,20,30]
Output: [4,1,2,3]
*/
    
    public int[] arrayRankTransform(int[] A) {
        if (A == null ||A.length < 1) return A;
        
        int[] B = Arrays.copyOf(A, A.length);
        
        Arrays.sort(B);
        Map<Integer, Integer> map = new HashMap<>();
        for(int b : B) {
            map.putIfAbsent(b, map.size() + 1);
        }
        
        int[] res = new int[A.length];
        for(int i = 0; i <A.length; i++) res[i] = map.get(A[i]);
        
        return res;
    }
    
    public int[] arrayRankTransform_BF(int[] A) {
        if (A == null ||A.length < 1) return A;
        
        int n = A.length;
        int[][] B = new int[n][2];
        for(int i = 0; i< n; i++) {
            B[i] = new int[]{A[i], i};
        }
        
        Arrays.sort(B, (a, b)->Integer.compare(a[0], b[0]));
        
        int[] res = new int[n];
        int idx = 1;
        res[B[0][1]] = idx;
        for(int i = 1; i< B.length; i++) {
            int[] cur = B[i], prev = B[i-1];
            if (cur[0] == prev[0]) {
                res[cur[1]] = idx;
            } else {
                res[cur[1]] = ++idx;
            }
        }
        
        return res;
        
    }
}