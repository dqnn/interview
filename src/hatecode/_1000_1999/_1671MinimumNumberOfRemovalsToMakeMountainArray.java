package hatecode._1000_1999;

import java.util.*;
public class _1671MinimumNumberOfRemovalsToMakeMountainArray {
/*
1671. Minimum Number of Removals to Make Mountain Array
You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array nums, return the minimum number of elements to remove to make nums a mountain array.

 

Example 1:

Input: nums = [1,3,1]
Output: 0
*/
    //thinking process: O(nlgn)/O(n)
    
    //the problem is to say: given one array, to make it a mountain array, like a1<...ai>...
    //return the min removal needed.
    
    //use the LIS solution, 
    public static int minimumMountainRemovals(int[] nums) {
        int leftLen = 0, rightLen = 0;
        int[] ldp = new int[nums.length], rdp = new int[nums.length];
        int[] l = new int[nums.length], r = new int[nums.length];
        
        for(int i = 0; i < nums.length; i++) {
            int pos = Arrays.binarySearch(ldp, 0, leftLen, nums[i]);
            pos = (pos < 0) ? (-(pos) - 1) : pos;
            if(pos == leftLen) leftLen++;
            
            l[i] = pos;
            ldp[pos] = nums[i];
        }
        
        System.out.println(Arrays.toString(ldp));
        for(int i = nums.length-1; i >= 0 ; i--) {
            int pos = Arrays.binarySearch(rdp, 0, rightLen, nums[i]);
            pos = (pos < 0) ? (-(pos) - 1) : pos;
            if(pos == rightLen) {
                rightLen++;
            }
            r[i] = pos;
            rdp[pos] = nums[i];
        }
  
        System.out.println(Arrays.toString(rdp));
        
       
        int res = nums.length;
        
        for(int i = 1; i < nums.length-1; i++) {
            int elementsToRemove = nums.length-(l[i] + r[i] + 1);
            if(l[i] != 0 && r[i] != 0)
                res = Math.min(res, elementsToRemove);
        }
        return res;
    }
    
    //brute force O(n^2)/O(n)
    public static int minimumMountainRemovals_SQUARE(int[] nums) {
        int n = nums.length;
        int[] LISForward = new int[n];
        int[] LISbackward = new int[n];
        
        Arrays.fill(LISForward, 1);
        Arrays.fill(LISbackward, 1);
        
        for(int i=0; i<n; i++) {
           for(int j=0; j<i; j++) {
               if(nums[i] > nums[j] && LISForward[i] < 1+LISForward[j]) {
                  LISForward[i] = 1 + LISForward[j]; 
               }
           } 
        }
        
         for(int i=n-1; i>=0; i--) {
           for(int j=i+1; j<n; j++) {
               if(nums[i] > nums[j] && LISbackward[i] < 1+LISbackward[j]) {
                  LISbackward[i] = 1 + LISbackward[j]; 
               }
           } 
        }
  
        int min = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            if(LISbackward[i] > 1 && LISForward[i] > 1) {
                min = Math.min(n-(LISbackward[i]+LISForward[i]-1), min);
            }
             
        }
        
        return min;
    }
    
    public static void main(String[] args) {
        System.out.println(minimumMountainRemovals(new int[] {2,1,1,5,6,2,3,1}));
    }
    
}