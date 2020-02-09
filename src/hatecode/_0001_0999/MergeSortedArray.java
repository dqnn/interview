package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MergeSortedArray
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 88. Merge Sorted Array
 */
public class MergeSortedArray {
    /**
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) 
to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

     time : O(m + n)
     space : O(1)

     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    // suppose we have enough storage, so we don'y need to have edge case
    //thinking process: 
    //scan from right is the key, because from left we need to re-arrange the array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        // we add them one by one, choose bigger one at last position
        while (i >= 0 && j >= 0) {
            nums1[k--] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
        }
        // means we still have some elements in nums2, for example nums2 has a lot of more elements than nums1
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}
