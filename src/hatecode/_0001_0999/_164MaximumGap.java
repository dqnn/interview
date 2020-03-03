package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaximumGap
 * Date : Aug, 2018
 * Description : 164. Maximum Gap
 */
public class _164MaximumGap {
    /**
     * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Return 0 if the array contains less than 2 elements.

Example 1:

Input: [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either
             (3,6) or (6,9) has the maximum difference 3.
Example 2:

Input: [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.

     time : O(n)
     space : O(n)


     * @param nums
     * @return
     */
    // this is bucket sort, so
    //1. bucket sort is mainly to categories data into several cages, each cage has some characters
    //2. 1 3 5 7， 4 numbers, so we need to have 3 buckets, 1-3  4-6  7-9  so first have 1 and 3, second 
    // we have 5, third we have 7
    
    //so we get the array max and min, and calculate the delta by size of the elements, so for every elmement, 
    //bucket index actually is the index of the elment in a sorted array. 
    
    //so 
    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int len = nums.length;
        int max = nums[0];
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        // this is the way how we calc the bucket gap or bucket capacity
        int gap = (int)Math.ceil((double)(max - min) / (len - 1));
        int[] bucketsMin = new int[len - 1];
        int[] bucketsMax = new int[len - 1];
        Arrays.fill(bucketsMax, Integer.MIN_VALUE);
        Arrays.fill(bucketsMin, Integer.MAX_VALUE);
        for (int num : nums) {
            if (num == min || num == max) continue;
            // this is how we calc the bucket idx，acutally it is the index after we sort the array
            int bucket = (num - min) / gap;
            bucketsMin[bucket] = Math.min(num, bucketsMin[bucket]);
            bucketsMax[bucket] = Math.max(num, bucketsMax[bucket]);
        }

        int res = 0;
        int pre = min;
        //why max(i) = len - 2 because bucketsMax length is len - 1
        for (int i = 0; i < len - 1; i++) {
            if (bucketsMin[i] == Integer.MAX_VALUE && bucketsMax[i] == Integer.MIN_VALUE) {
                continue;
            }
            //suppose q = bucketMax, p = bucketMin, we use q.min - p.max since 
            //they are adjacent in the array after 
            //sort
            res = Math.max(res, bucketsMin[i] - pre);
            pre = bucketsMax[i];
        }
        res = Math.max(res, max - pre);
        return res;
    }
}
