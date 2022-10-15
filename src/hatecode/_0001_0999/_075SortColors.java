package hatecode._0001_0999;

/**
 * Description : 75. Sort Colors
 */
public class _075SortColors {
    /**
Given an array with n objects colored red, white or blue, sort them in-place so that 
objects of the same color are adjacent, with the colors in the order red, white and 
blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, 
and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite 
array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
     time : O(n)
     space : O(1)

     * @param A
     */
    //thinking process:
    //since we only have 0, 1, 2 three types of data, so 0 must be put in first places, 
    // we use left to indicate current position is for 0
    // right to indicate position is for 2
    // 1 still there
    
    //this is like partition sort， 3 pointer templates，partition. 
    //we also have another partition templates, 
    
    //so the two different 3-way partition mainly difference is:
    //1. current this one is that we always the value we want to partition, so we 
    //want to compare
    //another paritition method is always choosing the first element in array, and 
    //paritition values by this array value
    //and this is the same method we re-arrange numbers zero, non-zero but keeping them in original order
    public void sortColors(int[] A) {
        if (A == null || A.length == 0) return;
        // indicate 0 count
        int l = 0;
        //indicate 2 count
        int r = A.length - 1;
        int idx = 0;
        while (idx <= r) {
            //left move to right only when nums[index] = 0
            if (A[idx] == 0) {
                swap(A, idx++, l++);
            } else if (A[idx] == 1) {
                idx++;
            //right move to left only left when nums[index] = 2
            } else {
                swap(A, idx, r--);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
