package leetcode;
import java.util.*;
class ReversePairs {
/*
 493. Reverse Pairs
Given an array nums, we call (i, j) an important reverse pair if i < j 
and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
*/
    //TreeMap, 2Dimension, BIT, this is 2D sorting we want to keep its position while want to 
    //query its value
    //O(nlgn)
    public static int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length-1);
    }
    private static int mergeSort(int[] nums, int s, int e){
        if(s>=e) return 0; 
        int mid = s + (e-s)/2; 
        int cnt = mergeSort(nums, s, mid) + mergeSort(nums, mid+1, e); 
        //position like: i mid j,  s<= i <= mid,that's to say, we want to compare
        //nums[i] and nums[j], if we can find one, then j need to j++, until loop break, then the 
        //number of qualified num is j - (mid +1) + 1 -1(last j is not qualified)
        
        //for example [1,3,2,3,1], when the loop it would be cnt = 0. s = 0, e= 1,
        //i = 0 <= mid, j = mid + 1 means the first on another half. so we would only loop 1 time
        // and then we would sort [0, 2(e + 1)] this part and return. 
        
        //
        for(int i = s, j = mid+1; i<=mid; i++){
            while(j<=e && nums[i]/2.0 > nums[j]) j++; 
            cnt += j-(mid+1); 
        }
        Arrays.sort(nums, s, e+1); 
        return cnt; 
    }
    //BST can also work, but O(n^2)
    
    public static void main(String[] args) {
        int[] in = {1,3,2,3,1};
        System.out.println(reversePairs(in));
    }
}