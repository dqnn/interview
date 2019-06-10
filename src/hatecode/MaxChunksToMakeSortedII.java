package hatecode;
public class MaxChunksToMakeSortedII {
    /*
     * 768. Max Chunks To Make Sorted II 
     * This question is the same as
     * "Max Chunks to Make Sorted" except the integers of the given array are not
     * necessarily distinct, the input array could be up to length 2000, and the
     * elements could be up to 10**8.
     * 
     * Given an array arr of integers (not necessarily distinct), we split the array
     * into some number of "chunks" (partitions), and individually sort each chunk.
     * After concatenating them, the result equals the sorted array.
     * 
     * What is the most number of chunks we could have made?
     * 
     * Example 1:
     * 
     * Input: arr = [5,4,3,2,1] Output: 1
     */
    //thinking process:
    
    //
     public int maxChunksToSorted(int[] nums) {
        //edge case
        if (nums == null || nums.length < 1) {
            return 0;
        }
        
        int n = nums.length;
        int[] minR = new int[n];
         
        minR[n-1] = nums[n-1];
        for(int i = n -2; i>=0; i--) {
            minR[i] = Math.min(minR[i+1], nums[i]);     
        }
        
        int cnt = 0;
        int maxL = Integer.MIN_VALUE;
        for(int i = 0; i < n - 1; i++) {
            maxL = Math.max(maxL, nums[i]);
            if (maxL <= minR[i + 1]) {
                cnt++;
            }
        }
        return cnt + 1;
    }
}