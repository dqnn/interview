package hatecode._1000_1999;

import java.util.*;
import java.util.stream.IntStream;
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
   * the problem is to say:  dialogonal visit the 2D matrix array
   * 
   * r +c = constant, so we can use this to visit the matrix
   */
    public int[] findDiagonalOrder(List<List<Integer>> A) {
        if(A == null || A.size() < 1 ) return new int[]{};
        
        /*
         * dialg have one patter nthat i + j = fixed value so we use a map key as i + j, value as its member, 
         * one key: we must visit last row, so the first element can be first column or last row.
         * 
         * 
         */
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        //map is not sorted, so need to know the max key, later we will need to visit the map from small to big values
        int max = 0;
        for(int i = A.size() - 1; i >= 0; i--) {
            for(int j = 0; j<A.get(i).size(); j++) {
                map.computeIfAbsent(i + j, v->new ArrayList<>()).add(A.get(i).get(j));
                max = Math.max(max, i + j);
            }
        }
        
        
        List<Integer> res = new ArrayList<>();
        IntStream.range(0, max+1).filter(key->map.get(key) != null).forEach(key->res.addAll(map.get(key)));
        
        return res.stream().mapToInt(x->x).toArray();
    }
}