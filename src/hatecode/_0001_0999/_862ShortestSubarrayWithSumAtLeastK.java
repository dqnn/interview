package hatecode._0001_0999;
import java.util.*;
public class _862ShortestSubarrayWithSumAtLeastK {
/*
862. Shortest Subarray with Sum at Least K
Return the length of the shortest, non-empty, contiguous subarray of 
A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.
Example 1:

Input: A = [1], K = 1
Output: 1
Example 2:

Input: A = [1,2], K = 4
Output: -1

Input: nums = [2,-1,2], k = 3
Output: 3
 */
    /*
     * 
     * thinking process: O(n)/O(n)
     * 
     * the problem is to say: given one array, return the max length of its sum at least k, 
     * 
     * TODO: how we can from sliding window to Deque?
P[x]=y 表示数列的前缀和，考虑点 x1 ,x2 若 x1<x2 ,且P[x1]>=p[x2]，那么选择x2肯定来的比x1短；
考虑y1<y2,若 满足 y1 ，y2都是x点，那么y1肯定比y2好。
维护一个双端队列，每次入队前 和 尾端的P[x]比较，若比原来的小则弹出原来的值；
和队首比较，若满足差值至少为K，则将队首的值出列。

     */
    //
    public static int shortestSubarray(int[] A, int K) {
        int N = A.length, res = N + 1;

        //to avoid overflow, use long instead of int
        long[] sum = new long[N + 1];
        for (int i = 0; i < N; i++) sum[i + 1] = sum[i] + A[i];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < N + 1; i++) {
            //this will make sure current head of q is qualified, update res every time, it is moving left
            while (q.size() > 0 && sum[i] - sum[q.getFirst()] >=  K)
                res = Math.min(res, i - q.pollFirst());
            
            // if sum[i] <= last one, this is how to move right pointer, make sure data in 
            //dequeue in increasing
            while (q.size() > 0 && sum[i] <= sum[q.getLast()]) q.pollLast();
            //add the index to last in queue
            q.addLast(i);
        }
        return res <= N ? res : -1;
    }
    
    //another problem, given an integer array, return longest subarray length && sum <= K, K > 0
    public static int longestSubarraySumLessThanKSomeNegative(int[] A, int K) {
        int N = A.length, res = 0;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) sum[i + 1] = sum[i] + A[i];
        System.out.println(Arrays.toString(sum));
        
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < N + 1; i++) {
            //this will make sure current head of q is qualified, update res every time, it is moving left
            while (q.size() > 0 && sum[i] - sum[q.getFirst()] > K) q.pollFirst();
            
            // if sum[i] <= last one, this is how to move right pointer, make sure data in 
            //dequeue in increasing
            while (q.size() > 0) {
                if (K > 0 && sum[i] < sum[q.getFirst()] )
                    q.pollLast();
            }
            //add the index to last in queue
            q.addLast(i);
            if (q.size() > 0) res = Math.max(res, i - q.getFirst() + 1);
            System.out.println(Arrays.deepToString(q.toArray()));
        }
        return res;
    }
    
    
    
    //we have 3 factors on this type questions. 
    /*
     *       positive integer, negative integer
     *       <=K, >=K
     *       shortest subarray, longest subarray, length = K, max sum
     *       
     *       
     */
    
    
    public static int longestSubArraySumLessThanKAllPositive(int[] A, int K) {
        int res = 0, sum=0;
        int l =0, r =0;
        while(r < A.length) {
            while(r<A.length && sum <= K) {
                sum += A[r];
                res = Math.max(res, r -l + 1);
                r++;
            }
            
            //this also work, no difference
            /*
            l++;
            sum -=A[l];
            */
            while(l < r && sum > K) {
                l++;
                sum -=A[l];
            }
        }
        
        return res;
    }
    
    
    public static void main(String[] args) {
        int[] in = {2,-1,2};
        System.out.println(shortestSubarray(in, 3));
        
        System.out.println(longestSubArraySumLessThanKAllPositive(new int[] {1,0,1,0,1,1,1,1}, 4));
        System.out.println(longestSubarraySumLessThanKSomeNegative(new int[] {1,-1,1,-1,-1,-1,-1,1}, 0));
    }
}