package hatecode._1000_1999;

import java.util.*;
public class _1424DiagonalTraverseII {
/*
1424. Diagonal Traverse II
Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.

 

Example 1:

 dialogonal 
Input: nums = [  [1,2,3,4,5],
                 [6,7],
                 [8],
                 [9,10,11],
                 [12,13,14,15,16]
              ]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
*/
  /*
   * thinking process: O(mn)/O(mn)
   * 
   * the problem is to say: 
   */
    public int[] findDiagonalOrder(List<List<Integer>> A) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        int maxKey = 0, size = 0;
        for(int r = A.size() - 1; r >=0; r--) {
            for(int c = 0; c < A.get(r).size(); c++) {
                map.computeIfAbsent(r +c, v->new ArrayList<>()).add(A.get(r).get(c));
                maxKey = Math.max(maxKey, r +c);
                size++;
            }
        }
        
        int[] res = new int[size];
        int idx = 0;
        for(int k = 0; k<=maxKey; k++) {
            List<Integer> list = map.get(k);
            if (list == null || list.size() ==0) continue;
            
            for(int v: list) res[idx++] = v;
        }
        
        return res;
    }
}