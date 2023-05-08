package _2000_2999;

import java.util.*;

public class _2670FindTheDistinctDifferenceArray {
    /*
    2670. Find the Distinct Difference Array
    You are given a 0-indexed array nums of length n.

The distinct difference array of nums is an array diff of length n such that diff[i] is equal to the number of distinct elements in the suffix nums[i + 1, ..., n - 1] subtracted from the number of distinct elements in the prefix nums[0, ..., i].

Return the distinct difference array of nums.

Note that nums[i, ..., j] denotes the subarray of nums starting at index i and ending at index j inclusive. Particularly, if i > j then nums[i, ..., j] denotes an empty subarray.

 

Example 1:

Input: nums = [1,2,3,4,5]
Output: [-3,-1,1,3,5]
    */

    /*
     * thinking process: O(n)/O(n)
     * 
     * the problem is to say:
     */
    
    
    public int[] distinctDifferenceArray(int[] A) {
        if (A == null || A.length < 1) return new int[]{};
    
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        Arrays.stream(A).forEach(e->map.put(e, map.getOrDefault(e, 0) + 1));
        
        int[] res = new int[A.length];
        for(int i = 0; i< A.length; i++){
            int a =A[i];
            set.add(a);
            map.put(a, map.get(a) - 1);
            if (map.get(a) == 0) map.remove(a);
            res[i] = set.size() - map.size();
        }
        
        return res;
    }

    public int[] distinctDifferenceArray_simple(int[] A) {
        if (A == null || A.length < 1) return new int[]{};
        
        int n = A.length;
        int[] l = new int[n], r = new int[n];
        Set<Integer> lSet = new HashSet<>();
        Set<Integer> rSet = new HashSet<>();
        for(int i = 0; i< n; i++) {
            lSet.add(A[i]);
            rSet.add(A[n-1-i]);
            l[i] = lSet.size();
            r[n-1-i] = rSet.size();
        }
        
        //System.out.println(Arrays.toString(l));
        //System.out.println(Arrays.toString(r));
        int[] res = new int[n];
        IntStream.range(0, n-1).forEach(i->res[i] = l[i] - r[i+1]);
        res[n-1] = l[n-1];
        
        return res;
    }
    
}