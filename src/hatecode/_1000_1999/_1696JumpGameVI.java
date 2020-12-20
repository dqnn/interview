package hatecode._1000_1999;

import java.util.PriorityQueue;

public class _1696JumpGameVI {
/*
1696. Jump Game VI
You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.

 

Example 1:

Input: nums = [1,-1,-2,4,-7,3], k = 2
Output: 7
*/
    /* thinking process: O(n)/O(n)
     * 
     * needs some time to understand this
     * why we use a DQ to process 
     */
    public int maxResult(int[] A, int k) {
        if(A.length == 1) return A[0];
        
        int res = Integer.MIN_VALUE;
        int n = A.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        pq.offer(new int[]{n - 1, A[n - 1]});
        
        for (int i = n - 2; i >= 0; i--){
            while(pq.peek()[0] > i + k){
                pq.poll();
            }
            res = A[i] + pq.peek()[1];
            pq.offer(new int[]{i, res});
        }
        
        return res;
    }
}