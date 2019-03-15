package hatecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayNesting {
    
    /*
    565. Array Nesting
    
    A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of set S, where      S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.

    Suppose the first element in S starts with the selection of element A[i] of index = i, the next element in S should be      A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate element occurs in S.

Example 1:
Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation: 
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
Note:
N is an integer within the range [1, 20,000].
The elements of A are all distinct.
Each element of A is an integer within the range [0, N-1].
     
    */
    
    /*
       Set to add check duplicate elements, this will have time Exceed problems
    */
    
    //brute force
    public int arrayNesting2(int[] nums) {
        //edge case
        if (nums == null || nums.length < 1) {
            return 0;
        }
        
        int len = nums.length - 1;
        int max = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i <= len; i++) {
            int temp = nums[i];
            while (!set.contains(temp)) {
                set.add(temp);
                temp = nums[temp];
            }
            max = Math.max(max, set.size());
            set.clear();;
        }
        
        return max;
    }
    
    // accepted solution, we have
    //Map : to store nums[i] <--> Set, 5 <--> 5, 6, 2, 0
    // list is to find the index of the duplicate elements
    public int arrayNesting(int[] nums) {
        //edge case
        if (nums == null || nums.length < 1) {
            return 0;
        }
        
        int len = nums.length - 1;
        int max = Integer.MIN_VALUE;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for(int i = 0; i <= len; i++) {
            int start = nums[i];
            int temp = start;
            Set<Integer> set = new LinkedHashSet<>();
            while (!set.contains(temp) && !map.containsKey(temp)) {
                set.add(temp);
                temp = nums[temp];
            }
            
            // 5 6 0 2 and 0 5 6 0 2, we will stop at 0 so we have to delete 2 in 0 array
            //this is to detect the head link to tail, so we would double this part
            if (map.containsKey(temp)) {
                List<Integer> list = new ArrayList<>(set);
                max = Math.max(max, set.size() + list.indexOf(start) + 1);
            } else {
                max = Math.max(max, set.size());
            }
            map.put(start, set);
        }
        
        return max;
    }
}