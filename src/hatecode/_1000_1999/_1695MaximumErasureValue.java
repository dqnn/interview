package hatecode._1000_1999;

import java.util.*;

public class _1695MaximumErasureValue {
/*
1695. Maximum Erasure Value
You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).

 

Example 1:

Input: nums = [4,2,4,5,6]
output：17
*/
    
    // thinking process : O(n)/O(n)
    
    /*
     * sliding window solution， 
     *  A = [4,2,4,5,6]
     *  when l = 0, r = 2, then we need to move l to 1,  
     *  A = [2,4,4,5,6]
     *  when l = 0, r = 2, then we need to move l to 2, because then we can have unique set  
     */
    public int maximumUniqueSubarray(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        int l = 0, r = 0;
        Set<Integer> set = new HashSet<>();
        int res = 0, tempSum  = 0;
        while (r < A.length) {
            //remove this while can also work
            while (r < A.length && !set.contains(A[r]) ) {
                set.add(A[r]);
                tempSum += A[r];
                res = Math.max(tempSum, res);
                r++;
            }
            
            while(r < A.length && l < r && set.contains(A[r])) {
                set.remove(A[l]);
                tempSum -= A[l];
                l++;
            }
        }
        
        return res;
    }
}