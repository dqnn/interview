package hatecode._0001_0999;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description : 239. Sliding Window Maximum
 */
public class _239SlidingWindowMaximum {
    /**
Given an array nums, there is a sliding window of size k which 
is moving from the very 
left of the array to the very right. You can only see the 
k numbers in the window. 
Each time the sliding window moves right by one position. 
Return the max sliding window.

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

direct solution will be O(nk), but we want O(n)
Follow up:
Could you solve it in linear time?
     Deque : 存的是index 从大到小排序

     time : O(n)
     space : O(n)

     * @param A
     * @param k
     * @return
     */

    /*
     * thinking process: O(n)/o(k)
     * 
     * the problem is to say: given one integer array A and one integer k as sliding window
     * size k, return each max value within the sliding window. for example as above
     * 
     * the brute force is to say: 
     * 
     */
    public int[] maxSlidingWindow(int[] A, int k) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[A.length + 1 - k];
        for (int i = 0; i < A.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.poll();
            }
            while (!deque.isEmpty() && A[deque.peekLast()] < A[i]) {
                deque.removeLast();
            }
            deque.offerLast(i);
            if ((i + 1) >= k) {
                res[i + 1 - k] = A[deque.peek()];
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
    
    //the left and right max only record the window local max, not global one
    public  int[] maxSlidingWindow3(final int[] A, final int k) {
        if (A == null || A.length < 1) {
           return A;
       }
        int n = A.length;
        int[] lmax = new int[n], rmax = new int[n];
        lmax[0] = A[0];
        rmax[n-1] = A[n-1];

       //scan from left and right in one pass
       //we use i % w == 0 to detect boundary of the sliding window
       for (int i = 1; i < A.length; i++) {
           lmax[i] = (i % k == 0) ? A[i] : Math.max(lmax[i - 1], A[i]);

           // scan from right, note the first window starts index= n - 2, because we do not need to 
           //consider the last element
           final int j = A.length - i - 1;
           rmax[j] = (j % k == 0) ? A[j] : Math.max(rmax[j + 1], A[j]);
       }

       int[] res = new int[n - k + 1];
       for(int i =0; i< n-k + 1; i++) {
           res[i] = Math.max(rmax[i], lmax[i + k - 1]);
       }
       
       return res;
   }
}
