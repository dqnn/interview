package hatecode._1000_1999;

import java.util.*;
public class _1207UniqueNumberOfOccurrences {
/*
1207. Unique Number of Occurrences
Given an array of integers arr, write a function that returns true if and 
only if the number of occurrences of each value in the array is unique.

 

Example 1:

Input: arr = [1,2,2,1,1,3]
Output: true
*/
    //thinking process: O(n)/O(1)
    
    //the problem is to say, given array A, return each element's occurrence 
    //is unique.
    
    //we use set to record each element's occurrence, and use map to record them, their 
    //length should be same
    public boolean uniqueOccurrences(int[] A) {
        if (A == null || A.length < 1) return true;
        
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(A).forEach(e->map.put(e, map.getOrDefault(e, 0) + 1));
        Set<Integer> set = new HashSet<>(map.values());
        
        return map.size() == set.size();
    }
}