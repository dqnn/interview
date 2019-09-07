package hatecode;
public class _704BinarySearch {
/*
704. Binary Search
Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to search target in nums. If target exists, then return its index, otherwise return -1.


Example 1:

Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
*/
    public int search(int[] nums, int target) {
        if (nums == null || nums.length < 1) return -1;
        
        int l = 0, r = nums.length -1;
        
        while(l <= r) {
            int mid = l + ((r- l) >> 1);
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) r = mid - 1;
            else l = mid + 1;
        }
        if (nums[l] == target) return l;
        else return -1;
    }
    //search template 2
    public int search_Template2(int[] nums, int target) {
        if (nums == null || nums.length < 1) return -1;
        
        int l = 0, r = nums.length -1;
        
        while(l + 1 < r) {
            int mid = l + ((r- l) >> 1);
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) r = mid;
            else l = mid;
        }
        //process l and r, return one of them 
        if (nums[l] == target) return l;
        else if (nums[r] == target) return r;
        else  return -1;
    }
    
    
  //search template 3
    public int search_Template3(int[] nums, int target) {
        if (nums == null || nums.length < 1) return -1;
        //note, this r = nums.length not nums.length -1
        int l = 0, r = nums.length;
        
        while(l < r) {
            int mid = l + ((r- l) >> 1);
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) r = mid;
            else l = mid + 1;
        }
        
        return -1;
    }
}