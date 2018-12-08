package leetcode;
import java.util.*;
public class ShortestSubarrayWithSumAtLeastK {
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
 */
    /*
P[x]=y 表示数列的前缀和，考虑点 x1 ,x2 若 x1<x2 ,且P[x1]>=p[x2]，那么选择x2肯定来的比x1短；
考虑y1<y2,若 满足 y1 ，y2都是x点，那么y1肯定比y2好。
维护一个双端队列，每次入队前 和 尾端的P[x]比较，若比原来的小则弹出原来的值；
和队首比较，若满足差值至少为K，则将队首的值出列。
     */
    public static int shortestSubarray(int[] A, int K) {
        int N = A.length, res = N + 1;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) sum[i + 1] = sum[i] + A[i];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < N + 1; i++) {
            //this will make sure current head of q is not qualified
            while (q.size() > 0 && sum[i] - sum[q.getFirst()] >=  K)
                res = Math.min(res, i - q.pollFirst());
            // if sum[i] <= last one, then we remove it.
            while (q.size() > 0 && sum[i] <= sum[q.getLast()]) q.pollLast();
            //add the index to last in queue
            q.addLast(i);
        }
        return res <= N ? res : -1;
    }
    
    public static void main(String[] args) {
        int[] in = {2,-1,2};
        System.out.println(shortestSubarray(in, 3));
    }
}