package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FindMinimuminRotatedSortedArrayII
 * Creator : duqiang
 * Date : July, 2018
 * Description : TODO
 */
public class _154FindMinimuminRotatedSortedArrayII {
    /**
     * 154. Find Minimum in Rotated Sorted Array II
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
     * 
     * 
     * 
     * 
     *
     *
     * 6 7 8 9 1 3 5;
     * 1 1 1 1 2 1
     *
     * time : O(logn)  worst: O(n);
     * space : O(1);
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
       
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] < nums[end]) {
                // why here cannot be mid - 1
                end = mid;
            } else if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end--;
            }
        }
        if (nums[start] < nums[end]) return nums[start];
        else return nums[end];
    }
    
    //also one solution that we does not need to check start and end value
    public int findMin2(int[] nums) {
        //edge case, discuss with interviewer about the return value
        if (nums == null || nums.length < 1) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        
        
        while(start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[end]) {
                end = mid;
            } else if (nums[mid] > nums[end]){
                start = mid + 1;
            } else {
                end --;
            }
        }
        
        return nums[start];
        
    }
    
    // this also still works becz trendency does not change
    public int findMin3(int[] nums) {
        //edge case, discuss with interviewer about the return value
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int pre = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if (pre > nums[i]) {
                return nums[i];
            }
        }
        
        return pre;
    
    }
}
