package hatecode._0001_0999;

import java.util.*;
public class _724FindPivotIndex {
    /*
     * 724. Find Pivot Index Given an array of integers nums, write a method that
     * returns the "pivot" index of this array.
     * 
     * We define the pivot index as the index where the sum of the numbers to the
     * left of the index is equal to the sum of the numbers to the right of the
     * index.
     * 
     * If no such index exists, we should return -1. If there are multiple pivot
     * indexes, you should return the left-most pivot index.
     * 
     * Example 1:
     * 
     * Input: nums = [1, 7, 3, 6, 5, 6] Output: 3
     */
    
     /*
      * interview friendly O(n)/O(1)

      left -- A[i] --- right 

      left sum == right sum, return i

      
      */
     public int pivotIndex(int[] A) {
         if(A == null || A.length < 1) return -1;
         
         int sum = Arrays.stream(A).sum();
         int lsum = 0;
         for(int i = 0; i<A.length; i++) {
             if(lsum == sum - lsum - A[i]) return i;
             lsum += A[i];
         }
         return -1;
     }
    
    
    public int pivotIndex2(int[] nums) {
        //edge case
        if (nums == null || nums.length <= 2) {
            return -1;
        }
        
        int pIndex = 0, len = nums.length - 1;
        while (pIndex <= len) {
            int lSum = 0, rSum = 0;
            for(int i = 0; i < pIndex; i++) {
                lSum += nums[i];
            }
            for(int i = pIndex + 1; i <= len; i++) {
                rSum += nums[i];
            }
            
            if (lSum == rSum) {
                return pIndex;
            } else {
                pIndex ++;
            }
        }
        
        return -1;
            
    }
}