package hatecode;

import java.util.*;
public class FindKthSmallestPairDistance {
/*
719. Find K-th Smallest Pair Distance
Given an integer array, return the k-th smallest distance among all the pairs. The distance of 
a pair (A, B) is defined as the absolute difference between A and B.

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
    //
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, l = 0, r = nums[n-1] - nums[0];
        while (l < r) {
            int cnt = 0, j = 0, mid = l + (r - l)/2;
            //count how many paris diff <= mid
            for (int i = 0; i < n; i++) {
                while (j < n && nums[j] - nums[i] <= mid) j++;
                cnt += j - i-1;
            }
            if (cnt >= k) r = mid;
            else l = mid + 1;
        }
        
        return l;
    }
    
    //bucket sort, O(n^2)
    public int smallestDistancePair_BucketSort(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        //either we have a loop to get its max-min
        int[] buckets = new int[1000000];
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<i;j++) {
                int diff = Math.abs(nums[j] - nums[i]);
                buckets[diff]++;
            }
        }
        int sum = 0;
        for(int i = 0; i< buckets.length; i++) {
            sum += buckets[i];
            if (sum >= k) return i;
        }
        return 0;
    }
}