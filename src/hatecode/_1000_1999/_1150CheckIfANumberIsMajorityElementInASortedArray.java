package hatecode._1000_1999;
public class _1150CheckIfANumberIsMajorityElementInASortedArray {
/*
1150. Check If a Number Is Majority Element in a Sorted Array
Given an array nums sorted in non-decreasing order, and a number target, return True if and only if target is a majority element.

A majority element is an element that appears more than N/2 times in an array of length N.

 

Example 1:

Input: nums = [2,4,5,5,5,5,5,6,6], target = 5
Output: true
*/
    //thinking process: O(n)/O(1)
    public boolean isMajorityElement_BF(int[] A, int target) {
        if (A == null || A.length < 1) return true;
        
        int n = A.length;
        int cnt = 1;
        for(int i = 1; i<n; i++) {
            if(A[i] == A[i-1]) cnt++;
            else cnt = 1;
            if(cnt > n / 2) return true;
        }
        return false;
    }
    //better one, O(lgn)/O(1)
    
    //since it is more than half of the array, so the middle one must be the value. 
    
    //but we need to validate it
    public boolean isMajorityElement(int[] nums, int target) {
        int firstIndex = firstOccur(nums, target);
        int plusNBy2Index = firstIndex + nums.length/2;
        
        //validate
        if (plusNBy2Index<nums.length 
            && nums[firstIndex] == target
            && nums[plusNBy2Index] == target) {
            return true;
        }
        
        return false;
    }
    
    //binary search
    private int firstOccur(int[] nums, int target) {
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = low + (high-low)/2;
            if (nums[mid] < target) low = mid + 1;
            else if (nums[mid] >= target) high = mid;
        }
        return low;
    }
}