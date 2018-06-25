package leetcode;
class MaxChunksToMakeSorted {
    
    /*
    769. Max Chunks To Make Sorted
    
    Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.

What is the most number of chunks we could have made?

Example 1:

Input: arr = [4,3,2,1,0]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
Example 2:

Input: arr = [1,0,2,3,4]
Output: 4
Explanation:
We can split into two chunks, such as [1, 0], [2, 3, 4].
However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
Note:

arr will have length in range [1, 10].
arr[i] will be a permutation of [0, 1, ..., arr.length - 1].
    
    
    */
    
    /*
    original: 0, 2, 1, 4, 3, 5, 7, 6
    max:      0, 2, 2, 4, 4, 5, 7, 7
    sorted:   0, 1, 2, 3, 4, 5, 6, 7
    index:    0, 1, 2, 3, 4, 5, 6, 7
    
    compared to the final sorted array, which means these value in the position is the boundary of each partition
    */
    public int maxChunksToSorted(int[] nums) {
        //edge case
        if (nums == null || nums.length < 1) {
            return 0;
        }
        
        int len = nums.length; 
        int max = Integer.MIN_VALUE, partitions = 0;
        for(int i = 0; i <= len - 1; i++) {
            max = Math.max(nums[i], max);
            if (max == i) {
                partitions += 1;
            }
        }
        return partitions;
    }
}