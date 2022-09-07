package hatecode._2000_2999;

import java.util.*;
public class _2023NumberOfPairsOfStringsWithConcatenationEqualToTarget {
/*
2023. Number of Pairs of Strings With Concatenation Equal to Target
Given an array of digit strings nums and a digit string target, return the number of pairs of indices (i, j) (where i != j) such that the concatenation of nums[i] + nums[j] equals target.

 

Example 1:

Input: nums = ["777","7","77","77"], target = "7777"
Output: 4
Explanation: Valid pairs are:
- (0, 1): "777" + "7"
- (1, 0): "7" + "777"
- (2, 3): "77" + "77"
- (3, 2): "77" + "77"
*/
    public int numOfPairs(String[] A, String target) {
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i<A.length; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }
        
        //System.out.println(map);
        
        int res = 0;
        for(String s: map.keySet()) {
            if (target.startsWith(s)) {
                if (target.equals(s + s)) {
                    int size = map.get(s);
                    res += size * (size - 1);
                } else if (map.containsKey(target.substring(s.length()))) {
                    res += map.get(s) * map.get(target.substring(s.length()));
                }
            }
        }
        return res;
    }
   
}