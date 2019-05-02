package hatecode;

import java.util.*;
public class FindKthSmallestPairDistance {
/*
719. Find K-th Smallest Pair Distance
Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0 
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
*/
    //this is pretty good question.
    //thinking process: 
    //given an array, to find the k-th smallest diff for all pairs. choose any 2 in this array. 
    //brute force is O(n^2lgn) which is straightforward
    //
    //[1,3,6,11] k = 2
    
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;
        //max and min diff within this array
        int l = 0;
        int r = nums[n - 1] - nums[0];
        //2nd template, cnt means, every time reset cnt = 0 
        //
        for (int cnt = 0; l < r; cnt = 0) {
            int mid = l + (r - l) / 2;
            //nums[j] - nums[i] <= mid
            for (int i = 0, j = 0; i < n; i++) {
                while (j < n && nums[j] <= nums[i] + mid) j++;
                cnt += j - i - 1;
            }

            if (cnt < k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}