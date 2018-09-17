package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SlidingWindowMaximum
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 239. Sliding Window Maximum
 */
public class SlidingWindowMaximum {
    /**
Given an array nums, there is a sliding window of size k which is moving from the very 
left of the array to the very right. You can only see the k numbers in the window. 
Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note: 
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
     Deque : 存的是index 从大到小排序

     time : O(n)
     space : O(n)

     * @param nums
     * @param k
     * @return
     */

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length + 1 - k];
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.offerLast(i);
            if ((i + 1) >= k) {
                res[i + 1 - k] = nums[deque.peek()];
            }
        }
        return res;
    }
    // simple solution
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return nums;
        }
        int len = nums.length;
        int[] res = new int[len - k + 1];
        int max = Integer.MIN_VALUE;
        int j = 0;
        for(int i = 0; i < nums.length;) {
            i = j;
            int p = i + k;
            while(i < p && i < nums.length) {
                max = Math.max(max, nums[i++]);
            }
            //System.out.println(String.format("i-%s, p-%s, max-%s", i, p, max));
            res[j++] = max;
            max = Integer.MIN_VALUE;
        }
        return res;
    }
    
    //this is interview friendly,scan from left and scan from right, then 
    // left most and right most to get max one
    public  int[] maxSlidingWindow3(final int[] in, final int w) {
        if (in == null || in.length < 1) {
           return in;
       }
       final int[] max_left = new int[in.length];
       final int[] max_right = new int[in.length];

       max_left[0] = in[0];
       max_right[in.length - 1] = in[in.length - 1];

       //scan from left
       for (int i = 1; i < in.length; i++) {
           max_left[i] = (i % w == 0) ? in[i] : Math.max(max_left[i - 1], in[i]);

           // scan from right
           final int j = in.length - i - 1;
           max_right[j] = (j % w == 0) ? in[j] : Math.max(max_right[j + 1], in[j]);
       }

       final int[] sliding_max = new int[in.length - w + 1];
       for (int i = 0, j = 0; i + w <= in.length; i++) {
           sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
       }

       return sliding_max;
   }
}
