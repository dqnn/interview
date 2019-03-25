package hatecode;
class MaxChunksToMakeSorted {
    
    /*
    769. Max Chunks To Make Sorted and Max Chunks To Make Sorted II
    
    Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  
    After concatenating them, the result equals the sorted array.

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
   
    so the array is [0, 1,2.... n], we want to know whether 0---k-1 the maxium is k, if it is, then this should be a chunk
    
    */
    public int maxChunksToSorted_I(int[] nums) {
        //edge case
        if (nums == null || nums.length < 1) {
            return 0;
        }
        
        int len = nums.length; 
        int max = Integer.MIN_VALUE, partitions = 0;
        for(int i = 0; i <= len - 1; i++) {
            max = Math.max(nums[i], max);
            // if the array sorted, it should be at the position with the idx = value, 
            // fist should be 1 no matter asc or desc, partitions has to be 1
            if (max == i) {
                partitions += 1;
            }
        }
        return partitions;
    }
    /*
    Algorithm: Iterate through the array, each time all elements to the left are smaller (or equal) to
     all elements to the right, there is a new chunck.
Use two arrays to store the left max and right min to achieve O(n) time complexity. Space complexity is O(n) too.
This algorithm can be used to solve ver2 too.

    */
  //interview friendly, the problem is to say given an array, distinct value(I), not distinct(II), 
    //if we can cut the array into several chunks, and sort each and then concated them, whole still sorted
    //so what's the max chunks can we get?
    
    //the key is to know whether one digit can be the partition boundary or not.
    //[0,3,2,4,5], maxL =[0,3,3,4,5], 
    //             minR =[0,2,2,4,5]
    //for one digit within this array, we can from left, the max value, from the right, 
    //the min value, if maxL <= minR which means from max(0,i) and min(i, n), 
    //the i could be the partition boundary, 
    public int maxChunksToSorted_I_II(int[] arr) {
        int n = arr.length;
        int[] maxL = new int[n];
        int[] maxR = new int[n];

        maxL[0] = arr[0];
        for (int i = 1; i < n; i++) {
            maxL[i] = Math.max(maxL[i-1], arr[i]);
        }

        maxR[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxR[i] = Math.min(maxR[i + 1], arr[i]);
        }

        int res = 1;
        for (int i = 0; i < n - 1; i++) {
            if (maxL[i] <= maxR[i + 1]) res++;
        }

        return res;
    }
    
    //furthermore, from last loop, we can cut maxL actually, as below:
    public int maxChunksToSorted_I_II_OneArray(int[] arr) {
        int n = arr.length;
        int[] maxR = new int[n];

        maxR[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxR[i] = Math.min(maxR[i + 1], arr[i]);
        }

        int res = 1;
        int maxL = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            maxL = Math.max(maxL, arr[i]);
            if (maxL <= maxR[i + 1]) res++;
        }

        return res;
    }
}