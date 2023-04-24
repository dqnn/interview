package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MergeSortedArray
 * Date : April, 2023
 * Description : 88. Merge Sorted Array
 */
public class _088MergeSortedArray {
    /**
Given two sorted integer arrays A and B, merge B into 
A as one sorted array.

Note:

The number of elements initialized in A and B are m and n respectively.
You may assume that A has enough space (size that is greater or equal to m + n) 
to hold additional elements from B.
Example:

Input:
A = [1,2,3,0,0,0], m = 3
B = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

     time : O(m + n)
     space : O(1)

     * @param A
     * @param m
     * @param B
     * @param n
     */
    // suppose we have enough storage, so we don'y need to have edge case
    //thinking process: 
    /*
     * scan from right is the key, because from left we need to re-arrange the array
     * i represents pointer to array A which element will be processed
     * j represents pointer to array B which element will be processed
     * after first while loop, there are 2 results:
     * 1. i > =0 while j < 0, we do not need to do anything, because A are all small and B are bigger, it is already sorted
     * 2. i < 0 whule j >= 0, B maybe longer or B has smaller elemeents than A, see examples in the code comments
     */
    public void merge(int[] A, int m, int[] B, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        // we add them one by one, choose bigger one at last position
        while (i >= 0 && j >= 0) {
            A[k--] = A[i] >= B[j] ? A[i--] : B[j--];
        }
        // means we still have some elements in B, for example B has a lot of more elements than A
        //or A always done, but B still have elements left

        // A = [0], m = 0, B = [1], n = 1 or 
        // A=[7,8,9,0,0,0], B =[1,2,3]

        while (j >= 0) {
            A[k--] = B[j--];
        }
    }
}
