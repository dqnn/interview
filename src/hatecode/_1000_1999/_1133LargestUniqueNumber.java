package hatecode._1000_1999;

import java.util.*;
public class _1133LargestUniqueNumber {
/*
1133. Largest Unique Number
Given an array of integers A, return the largest integer that only occurs once.

If no integer occurs once, return -1.

Example 1:

Input: [5,7,3,9,4,9,8,3,1]
Output: 8
*/
    //thinking process: O(n)/O(n)
    
    //simple ones, but no way to improve space complexity?
    public int largestUniqueNumber(int[] A) {
        if(A == null || A.length < 1) return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(A).forEach(e->map.put(e, map.getOrDefault(e, 0) + 1));
        int res = -1;
        for(int a : A) {
            if(map.get(a) == 1) {
                res = Math.max(res, a);
            }
        }
        return res;
    }
}